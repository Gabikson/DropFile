package com.gabiksoft.webapp.exceptions;


public class AccountNotActiveException extends Exception {

    public AccountNotActiveException() {
    }

    public AccountNotActiveException(String message) {

        super(message);
    }
}
