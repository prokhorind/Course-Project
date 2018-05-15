package com.project.course.service;

import com.project.course.dao.DaoFactory;
import com.project.course.dao.UserDao;
import com.project.course.dao.UserDaoImpl;
import com.project.course.entity.User;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;

/**
 * Created by kleba on 15.05.2018.
 */
public class UserService {
    private final DaoFactory factory = DaoFactory.getInstance();
    private UserDao userDao =factory.getUserDao();


    public User getUserById(long userId) throws ServiceException {
        User user;
        try {
            user = new User();
            try {
                TransactionUtil.beginTransaction();
               user =  userDao.getUserById(userId);
            } catch (DAOException e) {
                TransactionUtil.rollback();
            } finally {
                TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
