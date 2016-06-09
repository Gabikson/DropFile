package com.gabiksoft.webapp.utils;

import org.springframework.stereotype.Component;
import org.thymeleaf.Template;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.resourceresolver.FileResourceResolver;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Component
public class TemplateCompiler {

    private ThymeleafViewResolver thymeleafView;

    private Template template;

    private TemplateEngine templateEngine;

    private TemplateResolver templateResolver;

    public TemplateCompiler() {


    }
}
