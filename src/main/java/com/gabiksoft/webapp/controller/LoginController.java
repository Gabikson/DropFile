package com.gabiksoft.webapp.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gabiksoft.webapp.service.impl.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/login")
    public String goLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public @ResponseBody String doSignIn(@RequestParam("username") String username,
                           @RequestParam("password") String password) {

        JSONObject responce = new JSONObject();
        boolean loginResult = false;
        try {
            loginResult = securityService.doAuthentication(username, password);
        }catch(BadCredentialsException e){
            responce.put("errorMessage","Invalid password");
        }catch (UsernameNotFoundException e){
            responce.put("errorMessage","User not found");
        }
        responce.put("login",loginResult);
        return responce.toString();
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public @ResponseBody String doSignOut() {
        JSONObject responce = new JSONObject();
        securityService.doUnAuthentication();
        responce.put("logout",true);
        return responce.toString();
    }
}
