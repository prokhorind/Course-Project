package com.project.course.service;

import com.project.course.dao.DaoFactory;
import com.project.course.dao.RoleDao;
import com.project.course.dao.UserDao;
import com.project.course.dao.UserRoleDao;
import com.project.course.entity.Order;
import com.project.course.entity.Role;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.exception.ServiceException;
import com.project.course.transaction.TransactionUtil;
import com.project.course.util.Roles;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by kleba on 06.05.2018.
 */
public class LoginService {

    private final DaoFactory factory = DaoFactory.getInstance();
    Logger logger = LoggerFactory.getLogger(LoginService.class);
    private UserDao userDao = factory.getUserDao();
    private UserRoleDao userRoleDao = factory.getUserRoleDao();
    private RoleDao roleDao = factory.getRoleDao();

    public String login(String login,String password) throws ServiceException {
        Role role = null;
        try {
            TransactionUtil.beginTransaction();
            long userId = userDao.getUserId(login, DigestUtils.md5Hex(password).toUpperCase());
            long roleId = userRoleDao.getRoleId(userId);
            role=  roleDao.getRoleById(roleId);
            TransactionUtil.commit();
        } catch (DAOException e) {
            logger.error("Transaction failed"+e);
            try {
                TransactionUtil.rollback();
            } catch (DataBaseException e1) {
                e1.printStackTrace();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } finally {
            try {
                TransactionUtil.endTransaction();
                logger.info("Transaction completed");
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return role.getName();
    }

    public String chooseMainPage(String role){
        if (role==null || role.isEmpty()){
             return "/pages/index.jsp";
        }else if(role.equalsIgnoreCase(Roles.MEMBER.toString())){
            return "/pages/member.jsp";
        }else if(role.equalsIgnoreCase(Roles.EMPLOYEE.toString())){
            return "/pages/employee.jsp";
        }else if(role.equalsIgnoreCase(Roles.ADMIN.toString())){
            return "/pages/admin.jsp";
        }else{
            return "/pages/index.jsp";
        }
    }

}
