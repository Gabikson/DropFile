package com.gabiksoft.webapp.engine.confirm.service;

import com.gabiksoft.webapp.engine.confirm.dao.ConfirmDAO;
import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.custom.service.GenericServiceImpl;
import com.gabiksoft.webapp.enums.ConfirmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ConfirmServiceImpl extends GenericServiceImpl<ConfirmDAO, Confirm> implements ConfirmService {

    @Autowired
    public ConfirmServiceImpl(ConfirmDAO dao) {
        this.dao = dao;
        this.dao.setEntityClass(Confirm.class);
    }

    @Override
    public List<Confirm> getNotExpired(boolean status, ConfirmType... confirmTypes) {
        return dao.findNotExpiried(status, confirmTypes).orElse(new ArrayList<>());
    }
}
