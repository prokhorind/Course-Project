package com.project.course.dao;

import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

/**
 * Created by kleba on 02.05.2018.
 */
public interface UserRoleDao {
    void addUserRole(long roleId,long userId) throws DAOException, DataBaseException;
    void updateUserRole(long roleId,long userId) throws DAOException, DataBaseException;
}
