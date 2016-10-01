package com.gabiksoft.webapp.engine.impl;

import com.gabiksoft.webapp.engine.settings.service.ServerSettings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class ServerSettingsImplTest {

    @Autowired
    private ServerSettings serverSettings;

    @Test
    public void readStringValueTest() throws Exception {
        String custom = serverSettings.readStringValue("custom", "host.address");
        System.out.println(custom);
    }
}