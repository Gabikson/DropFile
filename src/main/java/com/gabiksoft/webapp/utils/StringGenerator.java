package com.gabiksoft.webapp.utils;

import com.sun.deploy.util.SyncFileAccess;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import java.util.Random;

@Component
public class StringGenerator {

    public static enum MODE {MODE_DIGITS, MODE_LOWER_CASE_LETTERS, MODE_UPPER_CASE_LETTERS, MODE_ALL_LETTERS, MODE_ALL};

    private final String DIGITS = "0123456789";

    private final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Random random;

    private StringGenerator() throws OperationNotSupportedException {
        random = new Random();
    }

    public String generateString(int length, MODE mode) {
        StringBuilder result = new StringBuilder();
        String dictionary = setGeneratorMode(mode);
        int dictionaryLength = dictionary.length();
        for(int i = 0; i < length; i++) {
            result.append(dictionary.charAt(random.nextInt(dictionaryLength)));
        }
        return result.toString();
    }

    private String setGeneratorMode(MODE mode) {
        String dictionary = null;
        switch (mode) {
            case MODE_DIGITS:{
                dictionary = DIGITS;
                break;
            }
            case MODE_LOWER_CASE_LETTERS:{
                dictionary = LOWER_CASE_LETTERS;
                break;
            }
            case MODE_UPPER_CASE_LETTERS:{
                dictionary = UPPER_CASE_LETTERS;
                break;
            }
            case MODE_ALL_LETTERS:{
                dictionary = LOWER_CASE_LETTERS + UPPER_CASE_LETTERS;
                break;
            }
            case MODE_ALL:{
                dictionary = DIGITS + LOWER_CASE_LETTERS + UPPER_CASE_LETTERS;
                break;
            }
            default:{
                throw new UnsupportedOperationException();
            }
        }
        return dictionary;
    }
}
