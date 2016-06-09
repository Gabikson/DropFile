package com.gabiksoft.webapp.service;

import com.gabiksoft.webapp.exceptions.AccountNotActiveException;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityService {

    boolean doAuthentication(String username, String password) throws UsernameNotFoundException, BadCredentialsException, AccountNotActiveException;

    void doUnAuthentication();

    User getSecurityUser() throws UserNotAuthenticated;
}
