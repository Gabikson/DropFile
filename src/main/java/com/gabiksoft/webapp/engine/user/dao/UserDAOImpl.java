package com.gabiksoft.webapp.engine.user.dao;


import com.gabiksoft.webapp.engine.custom.dao.DAOImpl;
import com.gabiksoft.webapp.engine.user.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {
}
