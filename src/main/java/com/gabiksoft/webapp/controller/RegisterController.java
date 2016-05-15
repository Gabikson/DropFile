package com.gabiksoft.webapp.controller;

import com.gabiksoft.webapp.constants.Messages;
import com.gabiksoft.webapp.constants.Roles;
import com.gabiksoft.webapp.email.MailClient;
import com.gabiksoft.webapp.entity.Role;
import com.gabiksoft.webapp.entity.User;
import com.gabiksoft.webapp.service.FileService;
import com.gabiksoft.webapp.utils.JSONResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gabiksoft.webapp.service.RoleService;
import com.gabiksoft.webapp.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService avatarService;

    @Autowired
    private MailClient mail;

    @RequestMapping("/register")
    public String goRegister(HttpServletRequest request) {
        mail.setAddressTo("gabikson@gmail.com");
        mail.sendMessage("Account confirm", "Please go to: http://www.google.com.ua");
        return "registration";
    }

    @RequestMapping(value = "/registerme", method = RequestMethod.POST)
    public @ResponseBody String registerUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("email") String email,
                            @RequestParam("avatar") MultipartFile avatar) {
        if (userService.userWithNameExists(username)) {
            return new JSONResponse(1, Messages.USER_NAME_ALREADY_EXISTS).toString();
        }

        Set<Role> userRole = new HashSet<Role>();
        userRole.add(getRoleUser());
        User user = new User(username, password, true, email, userRole);
        userService.create(user);

        try {
            avatarService.save(avatar.getOriginalFilename(), avatar.getBytes());
        } catch (IOException e) {
            return new JSONResponse(1, e.getMessage()).toString();
        }

        return new JSONResponse().toString();
    }

    private Role getRoleUser() {
        return roleService.findByFieldValue("ROLE", Roles.ROLE_USER);
    }
}
