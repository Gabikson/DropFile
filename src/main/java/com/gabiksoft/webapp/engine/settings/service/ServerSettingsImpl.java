package com.gabiksoft.webapp.engine.settings.service;

import com.gabiksoft.webapp.utils.ResourcesPropertiesBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ServerSettingsImpl implements ServerSettings {

    private final String SERVER_CONFIG_PATH = "server/";

    @Autowired
    private ResourcesPropertiesBundle resourcesPropertiesBundle;


    @Override
    public Properties readAll(String configName) {
        return resourcesPropertiesBundle.readConfigs(SERVER_CONFIG_PATH + configName);
    }

    @Override
    public String readStringValue(String configName, String property) {
        return resourcesPropertiesBundle.readConfigs(SERVER_CONFIG_PATH + configName).getProperty(property);
    }

    @Override
    public int readIntValue(String configName, String property) {
        return Integer.parseInt(resourcesPropertiesBundle.readConfigs(SERVER_CONFIG_PATH + configName).getProperty(property));
    }

    @Override
    public boolean readBoolValue(String configName, String property) {
        return Boolean.parseBoolean(resourcesPropertiesBundle.readConfigs(SERVER_CONFIG_PATH + configName).getProperty(property));
    }

    @Override
    public String writeStringValue(String configName, String property, String value) {
        return null;
    }

    @Override
    public int writeIntValue(String configName, String property, int value) {
        return 0;
    }

    @Override
    public boolean writeBoolValue(String configName, String property, boolean value) {
        return false;
    }
}
