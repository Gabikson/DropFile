package com.gabiksoft.webapp.controller;

import com.gabiksoft.webapp.service.impl.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private SecurityService securityService;


    @RequestMapping(value = {"/","index"})
    public String goHome(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("avatar", securityService.getCurrentUser());
        return "index";
    }
}
