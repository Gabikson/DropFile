package com.gabiksoft.webapp.utils;

import com.gabiksoft.webapp.email.MailClient;
import com.gabiksoft.webapp.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.context.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class TemplateCompilerTest {

    @Autowired
    private TemplateCompiler templateCompiler;

    @Autowired
    private EmailService emailService;

    @Test
    public void testCompile() throws Exception {
        String confirmUrl = "http://www.google.com.ua";
       String html = templateCompiler.compile("email", Locale.forLanguageTag("uk"), Collections.singletonMap("link", confirmUrl));
        System.out.println(html);
       // emailService.sendHtmlMessage("Title", "gabikson@gmail.com", html);
    }
}