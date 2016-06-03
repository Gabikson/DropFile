package com.gabiksoft.webapp.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@RunWith(JUnit4.class)
public class StringGeneratorTest {

    @Autowired
    private StringGenerator stringGenerator;

    @Test
    public void GenerateStringTest() throws Exception {
        stringGenerator.generateString(10, StringGenerator.MODE.MODE_ALL);
    }
}