package com.gabiksoft.webapp.service;


import java.util.List;

public interface GenericService<T> {

    void create(T entity);

    void update(T entity);

    void delete(int id);

    T getById(int id);

    T findByFieldValue(String field, String value);

    List<T> getAll();

}
