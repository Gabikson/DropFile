package com.gabiksoft.webapp.service.impl;

import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabiksoft.webapp.service.RoleService;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl extends GenericSeviceImpl<Role> implements RoleService {

    @Autowired
    public RoleServiceImpl(DAO<Role> dao) {
        this.dao = dao;
        this.dao.setEntityClass(Role.class);
    }

}
