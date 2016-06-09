package com.gabiksoft.webapp.service.impl;


import com.gabiksoft.webapp.exceptions.AccountNotActiveException;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import com.gabiksoft.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public boolean doAuthentication(String username, String password) throws UsernameNotFoundException, BadCredentialsException, AccountNotActiveException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        User user = (User) userDetailsService.loadUserByUsername(username);
        if (!user.isEnabled()) {
            throw new AccountNotActiveException(username);
        }
        authenticationToken.setDetails(user);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication.isAuthenticated();
    }

    public void doUnAuthentication() {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        SecurityContextHolder.clearContext();
    }

    public User getSecurityUser() throws UserNotAuthenticated {
        User user = null;
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new UserNotAuthenticated("No authentication found at current context");
        }
        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;

    }
}
