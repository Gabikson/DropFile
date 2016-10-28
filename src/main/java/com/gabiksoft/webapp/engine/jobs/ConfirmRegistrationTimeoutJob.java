package com.gabiksoft.webapp.engine.jobs;


import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.engine.confirm.service.ConfirmService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ConfirmRegistrationTimeoutJob implements Job {

    Logger logger = LoggerFactory.getLogger(ConfirmRegistrationTimeoutJob.class);

    @Autowired
    private ConfirmService confirmService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Confirm> notExpired = confirmService.getNotExpired(true);
        notExpired.forEach(entity -> entity.setStatus(false));
        confirmService.update(notExpired);
    }

}
