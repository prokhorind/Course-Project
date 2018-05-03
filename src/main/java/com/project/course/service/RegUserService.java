package com.project.course.service;


import com.project.course.dao.DaoFactory;
import com.project.course.dao.RoleDao;
import com.project.course.dao.UserDao;
import com.project.course.dao.UserRoleDao;
import com.project.course.entity.User;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;
import org.apache.commons.codec.digest.DigestUtils;

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
                long userId=userDao.getUserIdByEmail(user.getEmail());
                long roleId=roleDao.getRoleId("member");
                userRoleDao.addUserRole(roleId,userId);
                TransactionUtil.commit();
            } catch (DAOException e) {
               TransactionUtil.rollback();
            } finally {
               TransactionUtil.endTransaction();
            }
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }
}
