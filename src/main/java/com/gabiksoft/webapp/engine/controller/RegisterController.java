package com.gabiksoft.webapp.engine.controller;

import com.gabiksoft.webapp.constants.Messages;
import com.gabiksoft.webapp.constants.Roles;
import com.gabiksoft.webapp.constants.URLS;
import com.gabiksoft.webapp.constants.settings.Host;
import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.confirm.service.ConfirmService;
import com.gabiksoft.webapp.engine.custom.exception.EntityNotFoundException;
import com.gabiksoft.webapp.engine.email.service.EmailService;
import com.gabiksoft.webapp.engine.settings.service.ServerSettings;
import com.gabiksoft.webapp.engine.storage.service.FileService;
import com.gabiksoft.webapp.engine.user.entity.Role;
import com.gabiksoft.webapp.engine.user.entity.User;
import com.gabiksoft.webapp.engine.user.service.RoleService;
import com.gabiksoft.webapp.engine.user.service.UserService;
import com.gabiksoft.webapp.enums.ConfirmType;
import com.gabiksoft.webapp.utils.JSONResponse;
import com.gabiksoft.webapp.utils.StringGenerator;
import com.gabiksoft.webapp.utils.TemplateCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
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
    private EmailService emailService;

    @Autowired
    private StringGenerator stringGenerator;

    @Autowired
    private ConfirmService confirmService;

    @Autowired
    private TemplateCompiler compiler;

    @Autowired
    private ServerSettings serverSettings;

    @RequestMapping("/register")
    public String goRegister(HttpServletRequest request) {
        return "registration";
    }

    @RequestMapping(value = "/registerme", method = RequestMethod.POST)
    public
    @ResponseBody
    String registerUser(HttpServletRequest request,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email,
                        @RequestParam(value = "avatar", required = false) MultipartFile avatar) throws Exception {
        if (userService.userWithNameExists(username)) {
            return new JSONResponse(1, Messages.USER_NAME_ALREADY_EXISTS).toString();
        }

        Properties customProperties = serverSettings.readAll(Host.RESOURCE_NAME);

        String baseUrl = customProperties.getProperty(Host.HOST_PREFIX).concat(customProperties.getProperty(Host.HOST_NAME)
                .concat(customProperties.getProperty(Host.HOST_PORT) != null? ":" + customProperties.getProperty(Host.HOST_PORT) : ""));

        String avatarFileName = null;

        if (avatar != null) {
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

        Confirm confirm = buildConfirm(user.getId(), ConfirmType.ACCOUNT,
                stringGenerator.generateString(20, StringGenerator.MODE.MODE_LOWER_CASE_LETTERS));
        confirmService.create(confirm);

        String confirmUrl = baseUrl + URLS.ACCOUNT_CONFIRM_URL + confirm.getValue();

        emailService.sendHtmlMessage(user.getEmail(), "Account confirm", compiler.compile("email", LocaleContextHolder.getLocale(), Collections.<String, Object>singletonMap("link", confirmUrl)));
        return new JSONResponse().toString();
    }

    @RequestMapping(value = "/account/confirm/{activation_id}", method = RequestMethod.GET)
    public String confirmEmail(@PathVariable(value = "activation_id") String activationId) throws EntityNotFoundException {
        String userId = confirmService.findByFieldValue("value", activationId).getIdType();
        User user = userService.getById(userId);
        user.setEnabled(true);
        userService.update(user);
        return "accountconfirm";
    }

    private Role getRoleUser() throws EntityNotFoundException {
        return roleService.findByFieldValue(Roles.ROLE_FIELD_NAME, Roles.ROLE_USER);
    }

    private Confirm buildConfirm(String userId, ConfirmType confirmType, String value) {
        Timestamp expirationTime = Timestamp.valueOf(LocalDateTime.now().plusHours(24));
        return new Confirm(confirmType, userId, value, expirationTime);
    }
}
