package com.project.course.service;


import com.project.course.dao.*;
import com.project.course.entity.User;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.MailException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;
import com.project.course.util.Roles;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transaction;

public class RegUserService {
    private final DaoFactory factory = DaoFactory.getInstance();
    private Logger logger = LoggerFactory.getLogger(RegUserService.class);

    public void regUser(User user) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            RoleDao roleDao = factory.getRoleDao();
            UserRoleDao userRoleDao= factory.getUserRoleDao();
            try {
                TransactionUtil.beginTransaction();
                user.setPass(DigestUtils.md5Hex(user.getPass()).toUpperCase());
                userDao.insertUser(user);
                long userId=userDao.getUserId(user.getLogin());
                long roleId=roleDao.getRoleId(Roles.MEMBER.toString());
                userRoleDao.addUserRole(roleId,userId);
                TransactionUtil.commit();
                logger.info("user was registered");
                sendEmail(user);
                logger.info("email to"+user.getEmail()+" was sent");
            } catch (DAOException e) {
                logger.error(e.getLocalizedMessage());
               TransactionUtil.rollback();
            } finally {
               TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
            logger.error(e.getLocalizedMessage());
            throw new ServiceException(e);
        }
    }
    public void sendEmail(User user) throws ServiceException {
        MailDao mailDao = factory.getMailDao();
        try {
            mailDao.send(user);
        } catch (MailException e) {
            throw  new ServiceException(e);
        }
    }
}
