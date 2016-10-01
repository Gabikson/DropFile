package com.gabiksoft.webapp.engine.settings.service;


import java.util.Properties;

public interface ServerSettings {

    Properties readAll(String configName);

    String readStringValue(String configName, String property);

    int readIntValue(String configName, String property);

    boolean readBoolValue(String configName, String property);

    String writeStringValue(String configName, String property, String value);

    int writeIntValue(String configName, String property, int value);

    boolean writeBoolValue(String configName, String property, boolean value);
}
