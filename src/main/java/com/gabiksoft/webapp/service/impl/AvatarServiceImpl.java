package com.gabiksoft.webapp.service.impl;

import com.gabiksoft.webapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;

@PropertySource("classpath:storage.properties")
@Service
public class AvatarServiceImpl implements FileService {

    @Autowired
    private Environment environment;

    @Override
    public void save(String name, byte[] bytes) throws IOException {
        String avatarFolder = environment.getProperty("avatar.path") + "\\";
        OutputStream avatar = new FileOutputStream(avatarFolder + name);
        avatar.write(bytes);
        avatar.flush();
        avatar.close();
    }

    @Override
    public boolean remove(String name) {
        String avatarFolder = environment.getProperty("avatar.path") + "\\";
        return new File(avatarFolder+name).delete();
    }
}
