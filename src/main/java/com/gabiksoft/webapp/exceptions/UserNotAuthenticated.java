package com.gabiksoft.webapp.exceptions;


public class UserNotAuthenticated extends Exception {

    public UserNotAuthenticated() {
    }

    public UserNotAuthenticated(String message) {

        super(message);
    }
}
