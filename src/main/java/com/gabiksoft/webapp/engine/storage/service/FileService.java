package com.gabiksoft.webapp.engine.storage.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    public void save(String name, byte[] bytes) throws IOException;

    public boolean remove(String name);

    public InputStream get(String fileName) throws IOException;

}
