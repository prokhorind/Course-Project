package com.project.course.service;

import com.project.course.util.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by kleba on 17.05.2018.
 */

public class LoginServiceTest {

   private ServiceFactory sf = ServiceFactory.getInstatice();
   private LoginService ls;
    @Before
    public void setUp() throws Exception {
        ConnectionPool.getInstance().setDataSource(TestDataSource.getDataSource());
        ls =ServiceFactory.getInstatice().getLoginService();
    }

    @After
    public void tearDown() throws Exception {
        ls = null;
    }

    @Test
    public void login() throws Exception {
        String login ="1";
        String password ="1";
      assertNull(ls.login(login,password));
    }
    @Test
    public void normalLogin() throws Exception {
        String login = "klebanoff";
        String password = "15fghtkm";
        String role = "member";
        assertEquals(role,ls.login(login,password).getRole());
    }
    @Test
    public void chooseMainPage() throws Exception{
        String role = "member";
        String page = "/pages/member.jsp";
        assertEquals(page,ls.chooseMainPage(role));
    }

    @Test
    public void chooseMainPageFailed() throws Exception{
        String role = "";
        String page = "/pages/index.jsp";
        assertEquals(page,ls.chooseMainPage(role));
    }

}