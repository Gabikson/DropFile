package com.gabiksoft.webapp.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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