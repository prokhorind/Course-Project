package com.project.course.dao;

import com.project.course.entity.User;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

import java.util.Set;

/**
 * Created by kleba on 02.05.2018.
 */
public interface UserDao {
    void insertUser(User user)throws DAOException, DataBaseException;
    User getUserById(long id)throws DAOException, DataBaseException;
    void deleteUser(long id)throws DAOException, DataBaseException;
    long getUserId(String login)throws DAOException, DataBaseException;
    boolean isSameEmail(String email)throws DAOException, DataBaseException;
    boolean isSameLogin(String login)throws DAOException, DataBaseException;
    Set<User> getUsers(long from,long to)throws DAOException, DataBaseException;
    long getUserId(String login,String password)throws DAOException, DataBaseException;
}
