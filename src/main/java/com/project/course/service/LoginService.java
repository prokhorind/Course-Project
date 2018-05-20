package com.project.course.service;

import com.project.course.dao.DaoFactory;
import com.project.course.dao.RoleDao;
import com.project.course.dao.UserDao;
import com.project.course.dao.UserRoleDao;
import com.project.course.dto.User;
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

    public User login(String login, String password) throws ServiceException {
        User user = null;
        try {
            try {
                TransactionUtil.beginTransaction();
                user = userDao.getUser(login, DigestUtils.md5Hex(password).toUpperCase());
                TransactionUtil.commit();
                logger.info("Transaction completed");
            } catch (DAOException e) {
                logger.error("Transaction failed" + e);
                    TransactionUtil.rollback();
            } finally {
                TransactionUtil.endTransaction();
            }

        }catch (DataBaseException e){
            logger.error("Auth failed"+e.getMessage());
        }
        return user;
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
