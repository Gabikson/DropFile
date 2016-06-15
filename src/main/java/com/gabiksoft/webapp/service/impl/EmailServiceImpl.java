package com.gabiksoft.webapp.service.impl;


import com.gabiksoft.webapp.email.MailClient;
import com.gabiksoft.webapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.FileDataSource;
import javax.mail.MessagingException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailClient mailClient;

    @Override
    public void sendRawMessage(String receiver, String title, String text) {
        mailClient.sendSimpleMessage(receiver, title, text);
    }

    @Override
    public void sendHtmlMessage(String receiver, String title, String text, FileDataSource... attachments)
            throws MessagingException {
        mailClient.sendHtmlMessage(title, text, receiver, attachments);
    }
}
