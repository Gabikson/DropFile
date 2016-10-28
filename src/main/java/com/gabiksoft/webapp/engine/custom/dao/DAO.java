package com.gabiksoft.webapp.engine.custom.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Class<T> getEntityClass();

    void setEntityClass(Class<T> entityClass);

    void create(T entity);

    void update(T entity);

    void update(List<T> entities);

    void delete(T entity);

    Optional<T> read(String id);

    Optional<T> findByFieldValue(String field, String value);

    Optional<List<T>> getAll();
}