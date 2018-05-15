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

import javax.transaction.Transaction;

public class RegUserService {
    private final DaoFactory factory = DaoFactory.getInstance();

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
                sendEmail(user);
            } catch (DAOException e) {
               TransactionUtil.rollback();
            } finally {
               TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
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
