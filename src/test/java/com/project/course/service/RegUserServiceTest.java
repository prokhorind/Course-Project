package com.project.course.service;

import com.project.course.entity.User;
import com.project.course.exception.MailException;
import com.project.course.exception.ServiceException;
import com.project.course.util.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by kleba on 17.05.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegUserServiceTest {

    private ServiceFactory sf ;
    private RegUserService regUserService;
    private User normalUser;
    private User unNormalUser;
    @Before
    public void setUp() throws Exception {
        sf = ServiceFactory.getInstatice();
        regUserService = sf.getRegUserService();
        ConnectionPool.getInstance().setDataSource(TestDataSource.getDataSource());
        normalUser = new User("email@email.com","login","Passw0rd");
        unNormalUser = new User("email","klebanoff","pass");
    }
    @After
    public void tearDown() throws Exception{

    }

    @Test(expected = ServiceException.class)
    public void regUnnormalUser() throws Exception {
        regUserService.regUser(unNormalUser);
    }
    @Test(expected = ServiceException.class)
    public  void regUserWithSameEmail() throws Exception{
        unNormalUser.setEmail("den.u@mail.ru");
        regUserService.regUser(unNormalUser);

    }
}