package com.gabiksoft.webapp.engine.custom.service;


import com.gabiksoft.webapp.engine.custom.dao.DAO;
import com.gabiksoft.webapp.engine.custom.exception.EntityNotFoundException;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericServiceImpl<I extends DAO<T>, T> implements GenericService<T> {

    protected I dao;

    @Override
    public void create(T entity) {
        dao.create(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void update(List<T> entities) {
        if (!CollectionUtils.isEmpty(entities)) {
            dao.update(entities);
        }
    }

    @Override
    public void delete(String id) {
        dao.read(id).map(entity -> {
            dao.delete(entity);
            return null;
        });
    }

    @Override
    public T getById(String id) throws EntityNotFoundException {
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
