package com.gabiksoft.webapp.engine.jobs;


import com.gabiksoft.webapp.engine.confirm.service.ConfirmService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


public class ConfirmRegistrationTimeoutJob implements Job {

    @Autowired
    private ConfirmService confirmService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    private void invalidateConfirmRegistrationUrls() {

    }


}
