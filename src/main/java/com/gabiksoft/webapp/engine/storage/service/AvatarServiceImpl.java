package com.gabiksoft.webapp.engine.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;

@PropertySource("classpath:server/storage.properties")
@Service
public class AvatarServiceImpl implements FileService {

    @Autowired
    private Environment environment;

    @Override
    public void save(String name, byte[] bytes) throws IOException {
        String avatarFolder = environment.getProperty("avatar.path") + "\\";
        makeIfNotExists(avatarFolder);
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

    @Override
    public InputStream get(String fileName) throws IOException {
        String avatarFolder = environment.getProperty("avatar.path") + "\\";
        FileInputStream fis = new FileInputStream(avatarFolder+fileName);
        return fis;
    }

    private void makeIfNotExists(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdir();
        }
    }
}