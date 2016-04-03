package com.gabiksoft.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {


    @RequestMapping(value = {"/","index"})
    public String doTest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("tv","tttttttt");
        return "index";
    }
}
