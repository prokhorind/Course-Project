package com.project.course.service;

import com.project.course.dao.DaoFactory;
import com.project.course.dao.OrderDao;
import com.project.course.util.ConnectionPool;
import com.project.course.util.OrderStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by kleba on 17.05.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    private ServiceFactory sf;

    @InjectMocks
    private OrderService os;

    @Mock
    private OrderDao orderDao;

    @Before
    public void setUp() throws Exception {
        ConnectionPool.getInstance().setDataSource(TestDataSource.getDataSource());
        sf = ServiceFactory.getInstatice();
        os = sf.getOrderService();
        Mockito.when(orderDao.countOrders(OrderStatus.REG)).thenReturn(Long.valueOf(10));
        Mockito.when(orderDao.countOrders(1)).thenReturn(Long.valueOf(10));
        os.setOrderDao(orderDao);
    }
    @After
    public void tearDown() throws Exception{
        sf = null;
        os = null;
    }

    @Test
    public void getOrders() throws Exception {
        os.setOrderDao(DaoFactory.getInstance().getOrderDao());
        long from =0;
        long number = 5;
        int size = 5;
       assertEquals(size,os.getOrders(from,number).size());
    }
    @Test
    public void getFakeOrders() throws  Exception{
        long from = 500 ;
        long number = 5;
        int size = 0;
        assertEquals(size,os.getOrders(from,number).size());
    }

    @Test
    public void getMemberOrders1() throws Exception {
        int size = 0;
        int from = 0;
        int number = 5;
        String name ="checker";
        String role ="member";
        assertEquals(size,os.getOrders(name,role,from,number).size());
    }

    @Test
    public void getMemberOrders2() throws Exception {
        os.setOrderDao(DaoFactory.getInstance().getOrderDao());
        int size = 5;
        int from = 0;
        int number = 5;
        String name ="klebanoff";
        String role ="member";
        assertEquals(size,os.getOrders(name,role,from,number).size());
    }

    @Test
    public void countOrdersByStatus() throws Exception {
        int numberOfOrdersPerPage = 5;
        int pages = 2;
       assertEquals(pages,os.countOrdersByStatus(OrderStatus.REG,numberOfOrdersPerPage).size());
    }

    @Test
    public void getUserIdByOrderId() throws Exception {
        int fakeOrderId = 100;
        assertEquals(0,os.getUserIdByOrderId(fakeOrderId));
    }

    @Test
    public void getRealUserIdByOrderId() throws Exception {
        os.setOrderDao(DaoFactory.getInstance().getOrderDao());
        int realOrderId = 1;
        int userId = 1;
        assertEquals(1,os.getUserIdByOrderId(realOrderId));
    }

    @Test
    public void countUserOrder() throws Exception {
        String name ="klebanoff";
        int numberOfOrdersPerPage =5;
        int size =2;
        assertEquals(size,os.countUserOrder(name,numberOfOrdersPerPage).size()); ;
    }
    @Test
    public void countFakeUserOrder() throws Exception {
        os.setOrderDao(DaoFactory.getInstance().getOrderDao());
        String name ="fake";
        int numberOfOrdersPerPage =5;
        int size =0;
        assertEquals(size,os.countUserOrder(name,numberOfOrdersPerPage).size()); ;
    }


}