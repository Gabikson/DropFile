package com.gabiksoft.webapp.service.impl;


import com.gabiksoft.webapp.email.MailClient;
import com.gabiksoft.webapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.FileDataSource;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailClient mailClient;

    @Override
    public void sendRawMessage(String title, String receiver, String text) {
        mailClient.sendSimpleMessage(title, text, receiver);
    }

    @Override
    public void SendHtmlMessage(String title, String receiver, String text, FileDataSource... attachments) {

    }
}
