package com.gabiksoft.webapp.engine.confirm.dao;

import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.custom.dao.DAOImpl;
import com.gabiksoft.webapp.enums.ConfirmType;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ConfirmDAOImpl extends DAOImpl<Confirm> implements ConfirmDAO {

    @Override
    public Optional<List<Confirm>> findNotExpiried(boolean status, ConfirmType... type) {
        String queryString = "SELECT t FROM Confirm t WHERE status=:status"
                + (type.length > 0 ? " AND type IN:type" : "");

        Query query = entityManager.createQuery(queryString);
        query.setParameter("status", status);
        if (type.length > 0) {
            query.setParameter("type", type);
        }

        try {
            return Optional.ofNullable((List<Confirm>) query.getResultList());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
