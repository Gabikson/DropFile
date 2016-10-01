package com.gabiksoft.webapp.engine.confirm.service;

import com.gabiksoft.webapp.engine.custom.dao.DAO;
import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.custom.service.GenericSeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class ConfirmServiceImpl extends GenericSeviceImpl<Confirm> implements ConfirmService {

    @Autowired
    public ConfirmServiceImpl(DAO<Confirm> dao) {
        this.dao = dao;
        this.dao.setEntityClass(Confirm.class);
    }
}
