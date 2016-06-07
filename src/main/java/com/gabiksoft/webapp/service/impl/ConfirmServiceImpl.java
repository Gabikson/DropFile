package com.gabiksoft.webapp.service.impl;

import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.entity.Confirm;
import com.gabiksoft.webapp.service.ConfirmService;
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
