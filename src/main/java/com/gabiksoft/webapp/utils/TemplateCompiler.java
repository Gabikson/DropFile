package com.gabiksoft.webapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.messageresolver.SpringMessageResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Map;

@Component
public class TemplateCompiler {

    private ThymeleafViewResolver viewResolver;
    private SpringTemplateEngine templateEngine;
    private TemplateResolver templateResolver;

    @Autowired
    private ResourceBundleMessageSource messageSource;


    @PostConstruct
    private void init() throws Exception {
        templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("dynamic/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageSource(messageSource);
        templateEngine.setDialect(new SpringStandardDialect());

        templateEngine.afterPropertiesSet();
        if (!templateEngine.isInitialized()) {
            templateEngine.initialize();
        }
    }

    public String compile(String templateName, Locale locale, Map<String, Object> variables) throws Exception {
        Context context = new Context(locale, variables);
        String email = templateEngine.process(templateName, context);
        return email;
    }
}
