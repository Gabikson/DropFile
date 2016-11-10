package com.gabiksoft.webapp.engine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebSocketController {

    @RequestMapping("/sc")
    public String goRegister(HttpServletRequest request) {
        return "sc";
    }
}
