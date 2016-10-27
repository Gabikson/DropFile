package com.gabiksoft.webapp.engine.storage.service;

import com.gabiksoft.webapp.constants.settings.Storage;
import com.gabiksoft.webapp.engine.settings.service.ServerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class AvatarServiceImpl implements FileService {

    @Autowired
    private ServerSettings serverSettings;

    @Override
    public void save(String name, byte[] bytes) throws IOException {
        String avatarFolder = getUserPhotoPath() + "\\";
        makeIfNotExists(avatarFolder);
        OutputStream avatar = new FileOutputStream(avatarFolder + name);
        avatar.write(bytes);
        avatar.flush();
        avatar.close();
    }

    @Override
    public boolean remove(String name) {
        String avatarFolder = getUserPhotoPath() + "\\";
        return new File(avatarFolder+name).delete();
    }

    @Override
    public InputStream get(String fileName) throws IOException {
        String avatarFolder = getUserPhotoPath() + "\\";
        FileInputStream fis = new FileInputStream(avatarFolder+fileName);
        return fis;
    }

    private void makeIfNotExists(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    private String getUserPhotoPath() {
        return serverSettings.readStringValue(Storage.RESOURCE_NAME, Storage.USER_PHOTO_PATH);
    }
}
