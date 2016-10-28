package com.gabiksoft.webapp.engine.user.service;


import com.gabiksoft.webapp.engine.custom.dao.DAO;
import com.gabiksoft.webapp.engine.custom.service.GenericServiceImpl;
import com.gabiksoft.webapp.engine.security.service.SecurityServiceImpl;
import com.gabiksoft.webapp.engine.user.dao.UserDAO;
import com.gabiksoft.webapp.engine.user.entity.User;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<UserDAO, User> implements UserService {

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private SecurityServiceImpl securityService;


    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
        this.dao.setEntityClass(User.class);
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        dao.create(user);
    }

    @Override
    public boolean userWithNameExists(String login) {
        return dao.findByFieldValue("login", login).isPresent();
    }

    @Override
    public User getCurrentUser() throws UserNotAuthenticated {
        return dao.findByFieldValue("login", securityService.getSecurityUser().getUsername()).orElseThrow(() -> new UserNotAuthenticated());
    }

}
