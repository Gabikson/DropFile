package com.gabiksoft.webapp.engine.custom.service;


import com.gabiksoft.webapp.engine.custom.dao.DAO;
import com.gabiksoft.webapp.engine.custom.exception.EntityNotFoundException;

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
        dao.read(id).map(entity -> {dao.delete(entity); return null;});
    }

    @Override
    public T getById(int id) throws EntityNotFoundException {
        return dao.read(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public T findByFieldValue(String field, String value) throws EntityNotFoundException {
        return dao.findByFieldValue(field, value).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<T> getAll() throws EntityNotFoundException {
        return dao.getAll().orElseThrow(() -> new EntityNotFoundException());
    }
}
