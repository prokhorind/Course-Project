package com.project.course.dao;

import com.project.course.entity.Role;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

import java.util.Set;

/**
 * Created by kleba on 02.05.2018.
 */
public interface RoleDao {
    void addRole(Role role) throws DAOException, DataBaseException;
    void deleteRole(long id) throws DAOException, DataBaseException;
    Role getRoleById(long id) throws DAOException, DataBaseException;
    void updateRoleName(long id,String newName) throws DAOException, DataBaseException;
    long getRoleId(String name)throws DAOException, DataBaseException;
    Set<Role> getAllRoles() throws DAOException, DataBaseException;
}
