package com.project.course.service;

import com.project.course.dao.DaoFactory;
import com.project.course.dao.UserDao;
import com.project.course.dao.UserDaoImpl;
import com.project.course.entity.User;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kleba on 15.05.2018.
 */
public class UserService {
    private final DaoFactory factory = DaoFactory.getInstance();
    private UserDao userDao =factory.getUserDao();
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getUserById(long userId) throws ServiceException {
        User user;
        try {
            user = new User();
            try {
                TransactionUtil.beginTransaction();
               user =  userDao.getUserById(userId);
                logger.info("got user by id:"+userId);
            } catch (DAOException e) {
                logger.error("coudn't found user with id:"+userId);
                TransactionUtil.rollback();
                throw new ServiceException(e);
            } finally {
                TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
                logger.error(e.getMessage());
            throw new ServiceException(e);
        }
        return user;
    }
}
