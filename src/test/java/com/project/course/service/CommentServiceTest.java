package com.project.course.service;

import com.project.course.dao.CommentDao;
import com.project.course.dao.DaoFactory;
import com.project.course.dto.Comment;
import com.project.course.util.ConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by kleba on 17.05.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    private ServiceFactory sf;
    @Mock
    private CommentDao commentDao;
    @InjectMocks
    private CommentService cs;

    List<Comment> commentList;
    @Before
    public void setUp() throws Exception {
        List <Comment> commentsFromDao = new ArrayList(){
            {
                add(new Comment("user",new Date(new java.util.Date().getTime()),"comment"));
                add(new Comment("user2",new Date(new java.util.Date().getTime()),"comment2"));
            }
        };
        ConnectionPool.getInstance().setDataSource(TestDataSource.getDataSource());
        sf = ServiceFactory.getInstatice();
        cs = sf.getCommentService();
        Mockito.when(commentDao.getDTOComments(0,5)).thenReturn(commentsFromDao);
        Mockito.when(commentDao.getComments()).thenReturn(Long.valueOf(commentsFromDao.size()));
    }

    @After
    public void tearDown() throws Exception {
        cs = null;
    }



    @Test
    public void getNumberOfComments() throws Exception {
        int size =2 ;
        Set<Long> set = cs.getNumberOfComments(6);
        assertEquals(size,set.size());
    }

    @Test
    public void getComments() throws Exception {
        int size =2;
        cs.setCommentDao(commentDao);
        commentList =  cs.getComments(0,5);
        assertEquals(size,commentList.size());
    }

}