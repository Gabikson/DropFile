package com.gabiksoft.webapp.service.impl;

import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.entity.Confirm;
import com.gabiksoft.webapp.service.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmServiceImpl extends GenericSeviceImpl<Confirm> implements ConfirmService {

    private DAO<Confirm> dao;

    @Autowired
    public ConfirmServiceImpl(DAO<Confirm> dao) {
        this.dao = dao;
        this.dao.setEntityClass(Confirm.class);
    }
}
