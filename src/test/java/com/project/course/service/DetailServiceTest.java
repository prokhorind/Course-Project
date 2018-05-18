package com.project.course.service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.project.course.dao.DetailDao;
import com.project.course.entity.Detail;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.util.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by kleba on 17.05.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailServiceTest {
    private ServiceFactory sf = ServiceFactory.getInstatice();
    @InjectMocks
    private DetailService ds = sf.getDetailService();
    @Mock
    private DetailDao detailDao;

    private Set<Detail> detailSet;
    @Before
    public void setUp() throws Exception {

        detailSet = new HashSet(){
            {
                add(new Detail(1,"detail1","reason1",1));
                add(new Detail(2,"detail2","reason2",1));
                add(new Detail(3,"detail3","reason3",1));

            }
        };
        ConnectionPool.getInstance().setDataSource(TestDataSource.getDataSource());
        Mockito.when(detailDao.getDetails(1)).thenReturn(detailSet);
        ds.setDetailDao(detailDao);
    }

    @After
    public void tearDown() throws Exception {
        ds = null;
    }

    @Test(expected = ServiceException.class)
    public void addEmptyDetail() throws Exception {
        String[] names = {};
        String[] reasons ={};
        long orderId = 1;
        ds.addDetail(names,reasons,orderId);
    }

    @Test(expected = ServiceException.class)
    public void addDetailWithWorngOrderId() throws Exception {
        String[] names = {"name"};
        String[] reasons ={"reason"};
        long orderId = 6;
        ds.addDetail(names,reasons,orderId);
    }

    @Test
    public void getDetail() throws Exception {
        int size = 3;
        int realSize = ds.getDetail(1).size();
        assertEquals(size,realSize);
    }
    @Test
    public void getWrongNumberDetail() throws Exception {
        int size = 6;
        int realSize = ds.getDetail(1).size();
        assertNotEquals(size,realSize);
    }

}