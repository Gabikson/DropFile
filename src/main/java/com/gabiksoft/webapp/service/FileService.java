package com.gabiksoft.webapp.service;

import java.io.IOException;

public interface FileService {

    public void save(String name, byte[] bytes) throws IOException;

    public boolean remove(String name);

}
