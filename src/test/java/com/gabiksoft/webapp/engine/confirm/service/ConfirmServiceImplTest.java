package com.gabiksoft.webapp.engine.confirm.service;

import com.gabiksoft.webapp.engine.confirm.dao.ConfirmDAO;
import com.gabiksoft.webapp.engine.confirm.entity.Confirm;
import com.gabiksoft.webapp.enums.ConfirmStatus;
import com.gabiksoft.webapp.enums.ConfirmType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class ConfirmServiceImplTest {

    private Confirm confirm1 = new Confirm(ConfirmType.ACCOUNT, "12", "test1", Timestamp.from(Instant.now()), ConfirmStatus.ACTIVE);
    private Confirm confirm2 = new Confirm(ConfirmType.EMAIL, "34", "test2", Timestamp.from(Instant.now()), ConfirmStatus.INACTIVE );

    @Autowired
    private ConfirmDAO confirmDAO;

    @Before
    public void init() {
        confirmDAO.create(confirm1);
        confirmDAO.create(confirm2);
    }

    @After
    public void destroy() {
        confirmDAO.delete(confirm1);
        confirmDAO.delete(confirm2);
    }

    @Test
    @Transactional
    public void getNotExpiredTest() throws Exception {
        Optional<List<Confirm>> notExpiried = confirmDAO.findNotExpiried(ConfirmStatus.ACTIVE);
        //VERIFY
        assertTrue(notExpiried.isPresent());
    }
}