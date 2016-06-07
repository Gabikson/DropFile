package com.gabiksoft.webapp.dao.impl;

import com.gabiksoft.webapp.dao.DAO;
import com.gabiksoft.webapp.entity.User;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOImplTest {

    @Test
    public void DaoTest() {
        DAO<User> dao = new DAOImpl<User>();

    }
}