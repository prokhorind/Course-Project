package com.project.course.dao;

import com.project.course.entity.Role;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.transaction.ConnectionWrapper;
import com.project.course.transaction.TransactionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kleba on 02.05.2018.
 */
public class RoleDaoImpl implements RoleDao {

    @Override
    public void addRole(Role role)throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Insert into Role(name) values(?)";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setString(1,role.getName());
            ps.execute();
        } catch (SQLException e) {
            throw  new DAOException(e);
        }finally{
            try {
                con.close();
            }catch(SQLException e){
                throw new DataBaseException(e);
            }
        }

    }

    @Override
    public void deleteRole(long id)throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="DELETE FROM Role where roleId=?";
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
    }

    @Override
    public Role getRoleById(long id)throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select * from Role where roleId=?";
        Role role = null;
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                role.setId(rs.getLong(1));
                role.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return role;

    }

    @Override
    public void updateRoleName(long id, String newName) throws DAOException, DataBaseException{

    }

    @Override
    public long getRoleId(String name) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select roleId from Role where name=?";
        long roleId = 0;
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet rs= preparedStatement.executeQuery();
            if (rs.next()){
                roleId=rs.getLong(1);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return roleId;
    }

    @Override
    public Set<Role> getAllRoles() throws DAOException, DataBaseException {
        Set<Role> roleSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select * from Role";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                Role role = new Role(rs.getLong(1),rs.getString(2));
                roleSet.add(role);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return roleSet;
    }
}
