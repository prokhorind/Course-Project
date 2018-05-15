package com.project.course.service;

import com.project.course.dao.CommentDao;
import com.project.course.dao.DaoFactory;
import com.project.course.dao.UserDao;
import com.project.course.entity.Comment;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kleba on 11.05.2018.
 */
public class CommentService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public void addComment(String login,String comment) throws ServiceException {
        try {
            CommentDao commentDao = factory.getCommentDao();
            UserDao userDao = factory.getUserDao();
            try {
                TransactionUtil.beginTransaction();
                long userId = userDao.getUserId(login);
                commentDao.addComment(new Comment(userId, comment));
                TransactionUtil.commit();
            } catch (DAOException e) {
                TransactionUtil.rollback();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } finally {
                TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
        throw new ServiceException(e);
    }
    }
    public Set<Long> getNumberOfComments(long numberOfComments) throws ServiceException {

        Set<Long> longSet = new HashSet<>();
        try {
            CommentDao commentDao = factory.getCommentDao();
            long comments = 0;
            try {
                TransactionUtil.beginTransaction();
                comments = commentDao.getComments();
                long number = (long) Math.ceil((double) comments / numberOfComments);
                for (long i = 1; i <= number; i++) {
                    longSet.add(i);
                }
                TransactionUtil.commit();

            } catch (DAOException e) {
                throw new ServiceException(e);
            } finally {
                TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return longSet;
    }
    public List<com.project.course.dto.Comment> getComments(long limit,long offset) throws ServiceException {
        CommentDao commentDao =factory.getCommentDao();
        List<com.project.course.dto.Comment> comments;
        try {
            TransactionUtil.beginTransaction();
           comments=commentDao.getDTOComments(limit,offset);
            TransactionUtil.commit();
        } catch (DAOException e) {
            throw  new ServiceException(e);
        } catch (DataBaseException e) {
            throw  new ServiceException(e);
        }finally{
            try {
                TransactionUtil.endTransaction();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return  comments;
    }
}
