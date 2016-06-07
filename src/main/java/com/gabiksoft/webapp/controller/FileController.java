package com.gabiksoft.webapp.controller;


import com.gabiksoft.webapp.entity.User;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import com.gabiksoft.webapp.service.FileService;
import com.gabiksoft.webapp.service.UserService;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
    }
}
