package com.gabiksoft.webapp.engine.custom.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DAOImpl<T> implements DAO<T> {

    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public DAOImpl() {}

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
    public Optional<T> read(int id) {
        try {
            return Optional.ofNullable((T) entityManager.find(entityClass, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> findByFieldValue(String field, String value) {
        Query query = entityManager.createQuery("SELECT t FROM " + entityClass.getName() +
                " t WHERE " + field + "= '" + value + "'");
        try {
            return Optional.ofNullable((T) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<List<T>> getAll() {
        try {
            return Optional.ofNullable(entityManager.createQuery("from " + entityClass.getName()).getResultList());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
