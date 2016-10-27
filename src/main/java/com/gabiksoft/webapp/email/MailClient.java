package com.gabiksoft.webapp.email;

import com.gabiksoft.webapp.constants.settings.Email;
import com.gabiksoft.webapp.engine.settings.service.ServerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
@Scope("singleton")
public class MailClient {

    private final static String EMAIL_ENCODE = "utf-8";
    private final static String EMAIL_CONTENT_TYPE = "text/html; charset=";


    @Autowired
    private ServerSettings serverSettings;

    private String addressFrom;
    private String login;
    private String password;
    private Properties configProperties;

    @PostConstruct
    private void init() throws Exception {
        this.configProperties = serverSettings.readAll(Email.RESOURCE_NAME);
        this.login = configProperties.getProperty(Email.EMAIL_USER_NAME);
        this.password = configProperties.getProperty(Email.EMAIL_USER_PASSWORD);
        this.addressFrom = configProperties.getProperty(Email.SENDER_ADDRESS);
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
            message.setText(messageText, EMAIL_ENCODE);
            Transport.send(message, login, password);
        } catch (MessagingException e) {
            sendResult = false;
            e.printStackTrace();
        }
        return sendResult;
    }

    public boolean sendHtmlMessage(String subject, String messageText, String addressTo, FileDataSource... attachments) throws MessagingException {
        Session session = Session.getDefaultInstance(configProperties);
        MimeMessage message = new MimeMessage(session);

        message.setSubject(subject);
        message.setFrom(new InternetAddress(this.addressFrom));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressTo));

        BodyPart body = new MimeBodyPart();
        body.setContent(messageText, EMAIL_CONTENT_TYPE + EMAIL_ENCODE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(body);

        if (attachments.length > 0) {
            for (FileDataSource attachment : attachments) {
                body = new MimeBodyPart();
                body.setDataHandler(new DataHandler(attachment));
                body.setFileName(attachment.getName());
                multipart.addBodyPart(body);
            }
        }

        message.setContent(multipart);
        Transport.send(message, login, password);
        return true;
    }
}
