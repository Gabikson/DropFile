package com.gabiksoft.webapp.engine.controller;


import com.gabiksoft.webapp.engine.user.entity.User;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import com.gabiksoft.webapp.engine.storage.service.FileService;
import com.gabiksoft.webapp.engine.user.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {

    @Autowired
    private FileService avatarService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/avatar/userphoto", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFile() throws IOException, UserNotAuthenticated {
        User curUser = userService.getCurrentUser();
        String avatar = curUser.getAvatar();
        InputStream in;
        if(!StringUtils.isEmpty(avatar)) {
            in = avatarService.get(avatar);
        } else {
            return null;
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
    }
}
