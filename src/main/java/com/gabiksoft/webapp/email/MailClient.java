package com.gabiksoft.webapp.email;

import com.gabiksoft.webapp.utils.ResourcesPropertiesBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Component
@Scope("singleton")
public class MailClient {

    private final String EMAIL_PROPERTIES_NAME = "email";

    @Autowired
    private ResourcesPropertiesBundle propertiesBundle;

    private String addressFrom;
    private String login;
    private String password;
    private Properties configProperties;

    @PostConstruct
    private void init() throws Exception {
        this.configProperties = propertiesBundle.readConfigs(EMAIL_PROPERTIES_NAME);
        this.login = configProperties.getProperty("login");
        this.password = configProperties.getProperty("password");
        this.addressFrom = configProperties.getProperty("senderaddress");
    }

    public Properties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }

    public boolean sendSimpleMessage(String subject, String messageText, String addressTo) {
        boolean sendResult = true;
        Session session = Session.getDefaultInstance(configProperties);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(this.addressFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    addressTo));
            message.setSubject(subject);
            message.setText(messageText, "UTF-8");
            Transport.send(message, login, password);
        } catch (AddressException e) {
            sendResult = false;
            e.printStackTrace();
        } catch (MessagingException e) {
            sendResult = false;
            e.printStackTrace();
        }
        return sendResult;
    }

    public boolean sendHtmlMessage(String subject, String messageText, String addressTo, FileDataSource... attachments) throws MessagingException {
        boolean sendResult = true;
        Session session = Session.getDefaultInstance(configProperties);
        MimeMessage message = new MimeMessage(session);

        message.setSubject(subject);
        message.setFrom(new InternetAddress(this.addressFrom));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressTo));

        BodyPart body = new MimeBodyPart();
        body.setContent(messageText, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(body);

        if(attachments.length > 0) {
            for(int i = 0; i < attachments.length; i++) {
                body = new MimeBodyPart();
                body.setDataHandler(new DataHandler(attachments[i]));
                multipart.addBodyPart(body);
            }
        }

        message.setContent(multipart);
        Transport.send(message, login, password);
        return true;
    }
}
