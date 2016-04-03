package com.gabiksoft.webapp.service.impl;

import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabiksoft.webapp.service.RoleService;

import javax.transaction.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private DAO<Role> dao;


    @Autowired
    public RoleServiceImpl(DAO<Role> dao) {
        this.dao = dao;
        this.dao.setEntityClass(Role.class);
    }

    @Override
    @Transactional
    public void create(Role role) {
        dao.create(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        dao.update(role);
    }

    @Override
    @Transactional
    public void delete(int id) {
        dao.delete(dao.read(id));
    }

    @Override
    @Transactional
    public Role getById(int id) {
        return dao.read(id);
    }

    @Override
    @Transactional
    public Role findByFieldValue(String field, String value) {
        return dao.findByFieldValue(field, value);
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        return dao.getAll();
    }
}
