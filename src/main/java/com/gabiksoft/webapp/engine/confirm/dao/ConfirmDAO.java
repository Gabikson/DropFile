package com.gabiksoft.webapp.engine.confirm.dao;


import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.custom.dao.DAO;
import com.gabiksoft.webapp.enums.ConfirmType;

import java.util.List;

public interface ConfirmDAO extends DAO<Confirm> {

    List<Confirm> findNotExpiried(boolean status, ConfirmType... type);
}
