package com.gabiksoft.webapp.engine.custom.service;


import com.gabiksoft.webapp.engine.custom.exception.EntityNotFoundException;

import java.util.List;

public interface GenericService<T> {

    void create(T entity);

    void update(T entity);

    void delete(int id);

    T getById(int id) throws EntityNotFoundException;

    T findByFieldValue(String field, String value) throws EntityNotFoundException;

    List<T> getAll() throws EntityNotFoundException;

}
