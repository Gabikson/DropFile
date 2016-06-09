package com.gabiksoft.webapp.controller;

import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import com.gabiksoft.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/","index"})
    public String goHome(HttpServletRequest request, HttpServletResponse response) throws UserNotAuthenticated {
        request.setAttribute("has_avatar", userService.getCurrentUser().getAvatar() != null);
        return "index";
    }
}
