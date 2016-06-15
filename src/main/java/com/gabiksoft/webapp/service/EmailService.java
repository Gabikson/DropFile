package com.gabiksoft.webapp.service;


import javax.activation.FileDataSource;
import javax.mail.MessagingException;

public interface EmailService {

    void sendRawMessage(String receiver, String title, String text) throws MessagingException;

    void sendHtmlMessage(String receiver, String title, String text, FileDataSource... attachments) throws MessagingException;

}
