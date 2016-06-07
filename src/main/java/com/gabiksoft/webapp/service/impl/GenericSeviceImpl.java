package com.gabiksoft.webapp.service.impl;


import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.service.GenericService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericSeviceImpl<T> implements GenericService<T>{

    protected DAO<T> dao;

    @Override
    public void create(T entity) {
        dao.create(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void delete(int id) {
        dao.delete(dao.read(id));
    }

    @Override
    public T getById(int id) {
        return dao.read(id);
    }

    @Override
    public T findByFieldValue(String field, String value) {
        return dao.findByFieldValue(field, value);
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }
}
