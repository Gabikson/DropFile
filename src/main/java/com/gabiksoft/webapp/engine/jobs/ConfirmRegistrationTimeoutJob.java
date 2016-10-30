package com.gabiksoft.webapp.engine.jobs;

import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.confirm.service.ConfirmService;
import com.gabiksoft.webapp.enums.ConfirmStatus;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfirmRegistrationTimeoutJob {

    Logger logger = LoggerFactory.getLogger(ConfirmRegistrationTimeoutJob.class);

    @Autowired
    private ConfirmService confirmService;

    public void execute() throws JobExecutionException {
        List<Confirm> notExpired = confirmService.getNotExpired(ConfirmStatus.ACTIVE);
        notExpired.forEach(entity -> entity.setStatus(ConfirmStatus.INACTIVE));
        confirmService.update(notExpired);
    }

}
