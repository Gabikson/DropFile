package com.gabiksoft.webapp.engine.confirm.service;

import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.custom.service.GenericService;
import com.gabiksoft.webapp.enums.ConfirmType;

import java.util.List;

public interface ConfirmService extends GenericService<Confirm> {

    List<Confirm> getNotExpired(boolean status, ConfirmType... confirmTypes);

}
