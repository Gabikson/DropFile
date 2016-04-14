package com.gabiksoft.webapp.controller;

import com.gabiksoft.webapp.constants.Messages;
import com.gabiksoft.webapp.constants.Roles;
import com.gabiksoft.webapp.entity.Role;
import com.gabiksoft.webapp.entity.User;
import com.gabiksoft.webapp.utils.JSONResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gabiksoft.webapp.service.RoleService;
import com.gabiksoft.webapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String goRegister(HttpServletRequest request){

        return "registration";
    }

    @RequestMapping(value = "/registerme", method = RequestMethod.POST)
    public @ResponseBody String registerUser(@RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("email") String email)  {
    if(userService.userWithNameExists(username)){
        return new JSONResponse(1, Messages.USER_NAME_ALREADY_EXISTS).toString();
    }
        User user = new User();
        user.setLogin(username);
        user.setPassword(password);
        user.setEnabled(true);
        user.setEmail(email);
        Set<Role> userRole = new HashSet<Role>();
        userRole.add(getRoleUser());
        user.setRoles(userRole);
        userService.create(user);
        return "yus";
    }

    private Role getRoleUser() {
        return roleService.findByFieldValue("ROLE", Roles.ROLE_USER);
    }
}
