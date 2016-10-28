package com.gabiksoft.webapp.engine.user.service;

import com.gabiksoft.webapp.engine.custom.dao.DAO;
import com.gabiksoft.webapp.engine.user.dao.RoleDAO;
import com.gabiksoft.webapp.engine.user.entity.Role;
import com.gabiksoft.webapp.engine.custom.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends GenericServiceImpl<RoleDAO, Role> implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleDAO dao) {
        this.dao = dao;
        this.dao.setEntityClass(Role.class);
    }

}
