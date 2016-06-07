package com.gabiksoft.webapp.controller;

import com.gabiksoft.webapp.constants.Messages;
import com.gabiksoft.webapp.constants.Roles;
import com.gabiksoft.webapp.email.MailClient;
import com.gabiksoft.webapp.entity.Confirm;
import com.gabiksoft.webapp.entity.Role;
import com.gabiksoft.webapp.entity.User;
import com.gabiksoft.webapp.enums.ConfirmType;
import com.gabiksoft.webapp.service.ConfirmService;
import com.gabiksoft.webapp.service.FileService;
import com.gabiksoft.webapp.utils.JSONResponse;
import com.gabiksoft.webapp.utils.StringGenerator;
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

    @Autowired
    private StringGenerator stringGenerator;

    @Autowired
    private ConfirmService confirmService;

    @RequestMapping("/register")
    public String goRegister(HttpServletRequest request) {
        return "registration";
    }

    @RequestMapping(value = "/registerme", method = RequestMethod.POST)
    public @ResponseBody String registerUser(@RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("email") String email,
                                             @RequestParam(value = "avatar",required = false) MultipartFile avatar) {
        if (userService.userWithNameExists(username)) {
            return new JSONResponse(1, Messages.USER_NAME_ALREADY_EXISTS).toString();
        }

        String avatarFileName = null;

        if(avatar != null) {
            avatarFileName = avatar.getOriginalFilename();
            try {
                avatarService.save(avatarFileName, avatar.getBytes());
            } catch (IOException e) {
                return new JSONResponse(1, e.getMessage()).toString();
            }
        }

        Set<Role> userRole = new HashSet<Role>();
        userRole.add(getRoleUser());
        User user = new User(username, password, false, email, userRole, avatarFileName);
        userService.create(user);

        Confirm confirm = buildConfirm(String.valueOf(user.getId()), ConfirmType.ACCOUNT,
                stringGenerator.generateString(20, StringGenerator.MODE.MODE_LOWER_CASE_LETTERS));
        confirmService.create(confirm);


        mail.setAddressTo(user.getEmail());
        mail.sendMessage("Account confirm", "Please go to: http://localhost:8080/account/confirm/"+confirm.getValue());
        return new JSONResponse().toString();
    }

    @RequestMapping(value = "/account/confirm/{activation_id}", method = RequestMethod.GET)
    public String confirmEmail(@PathVariable(value="activation_id") String activationId) {
        String userId = confirmService.findByFieldValue("value", activationId).getIdType();
        User user = userService.getById(Integer.parseInt(userId));
        user.setEnabled(true);
        userService.update(user);
        return "emailconfirm";
    }

    private Role getRoleUser() {
        return roleService.findByFieldValue("ROLE", Roles.ROLE_USER);
    }

    private Confirm buildConfirm(String userId, ConfirmType confirmType, String value) {
       return new Confirm(confirmType, userId, value);
    }
}
