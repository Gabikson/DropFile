package com.gabiksoft.webapp.utils;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class StringGeneratorTest {

    @Autowired
    private StringGenerator stringGenerator;

    @Test
    public void GenerateStringTest() throws Exception {
        stringGenerator.generateString(10, StringGenerator.MODE.MODE_ALL);
    }
}