package com.gabiksoft.webapp.dao.impl;

import com.gabiksoft.webapp.dao.DAO;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DAOImpl<T> implements DAO<T> {

    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public DAOImpl() {
//        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
//                .getGenericSuperclass();
//        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T read(int id) {
        return (T) entityManager.find(entityClass, id);
    }

    @Override
    public T findByFieldValue(String field, String value) {
        return (T) entityManager.createQuery("from " + entityClass.getName() +
                " where " + field + "=" + value, entityClass).getSingleResult();
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("from " + entityClass.getName()).getResultList();
    }
}
