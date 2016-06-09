package com.gabiksoft.webapp.service.impl;


import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.entity.User;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import com.gabiksoft.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl extends GenericSeviceImpl<User> implements UserService {



    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private SecurityServiceImpl securityService;


    @Autowired
    public UserServiceImpl(DAO<User> dao) {
        this.dao = dao;
        this.dao.setEntityClass(User.class);
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        dao.create(user);
    }

    @Override
    public boolean userWithNameExists(String login){
        try{
            dao.findByFieldValue("login", login);
        }catch (NoResultException e){
            return false;
        }
        return true;
    }

    @Override
    public User getCurrentUser() throws UserNotAuthenticated {
        return dao.findByFieldValue("login", securityService.getSecurityUser().getUsername());
    }

}
