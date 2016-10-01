package com.gabiksoft.webapp.engine.user.service;

import com.gabiksoft.webapp.engine.custom.dao.DAO;
import com.gabiksoft.webapp.engine.user.entity.Role;
import com.gabiksoft.webapp.engine.custom.service.GenericSeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends GenericSeviceImpl<Role> implements RoleService {

    @Autowired
    public RoleServiceImpl(DAO<Role> dao) {
        this.dao = dao;
        this.dao.setEntityClass(Role.class);
    }

}
