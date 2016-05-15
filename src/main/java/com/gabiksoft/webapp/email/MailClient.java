package com.gabiksoft.webapp.email;

import com.gabiksoft.webapp.utils.ResourcesPropertiesBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@Scope("singleton")
public class MailClient {

    private final String EMAIL_PROPERTIES_NAME = "email";
    @Autowired
    private ResourcesPropertiesBundle propertiesBundle;

    private String addressFrom;
    private String addressTo;
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

    public MailClient() {}

    public MailClient(String from, String to) {
        this.addressFrom = from;
        this.addressTo = to;
    }

    public MailClient(String from, String to, String login, String pwd) {
        this.addressFrom = from;
        this.addressTo = to;
        this.login = login;
        this.password = pwd;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public Properties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }

    public boolean sendMessage(String subject, String messageText) {
        boolean sendResult = true;
        Session session = Session.getDefaultInstance(configProperties);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(this.addressFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    this.addressTo));
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
}
