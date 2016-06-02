package com.gabiksoft.webapp.service.impl;


import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.gabiksoft.webapp.service.UserService;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;



import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private DAO<User> dao;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;


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
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delete(int id) {
        dao.delete(dao.read(id));
    }

    @Override
    public User getById(int id) {
        return dao.read(id);
    }

    @Override
    public User findByFieldValue(String field, String value) {
        return dao.findByFieldValue(field, value);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    public boolean userWithNameExists(String login){
        try{
            dao.findByFieldValue("login", login);
        }catch (NoResultException e){
            return false;
        }
        return true;
    }
}
