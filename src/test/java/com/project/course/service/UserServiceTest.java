package com.project.course.service;

import com.project.course.entity.User;
import com.project.course.util.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kleba on 18.05.2018.
 */
public class UserServiceTest {

    private ServiceFactory sf;
    private UserService us;
    @Before
    public void setUp() throws Exception {
        ConnectionPool.getInstance().setDataSource(TestDataSource.getDataSource());
        sf = ServiceFactory.getInstatice();
        us = sf.getUserService();
    }

    @After
    public void tearDown() throws Exception {
        sf = null;
        us = null;
    }

    @Test
    public void getUserById() throws Exception {
        long wrongId = 115;
        User u =  us.getUserById(wrongId);
        assertNull(u);
    }
    @Test
    public void getUserByWithIdStoredInDb() throws Exception {
        long id = 1;
        User u =  us.getUserById(id);
        assertNotNull(u);
    }
}