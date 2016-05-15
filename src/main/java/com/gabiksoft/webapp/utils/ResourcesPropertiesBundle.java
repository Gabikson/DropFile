package com.gabiksoft.webapp.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public class ResourcesPropertiesBundle {

    private ResourcesPropertiesBundle()  {  }

    public Properties readConfigs(String name) {
        ResourceBundle bundle = ResourceBundle.getBundle(name, Locale.getDefault());
        Properties properties = new Properties();
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = bundle.getString(key);
            properties.put(key, value);
        }
        return properties;
    }
}