package com.gabiksoft.webapp.dao;

import java.util.List;

public interface DAO<T> {

    Class<T> getEntityClass();

    void setEntityClass(Class<T> entityClass);

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    T read(int id);

    T findByFieldValue(String field, String value);

    List<T> getAll();
}