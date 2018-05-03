package com.project.course.dao;

import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.transaction.ConnectionWrapper;
import com.project.course.transaction.TransactionUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by kleba on 02.05.2018.
 */
public class UserRoleDaoImp implements UserRoleDao {
    @Override
    public void addUserRole(long roleId, long userId) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Insert into userrole(userId,roleId) values(?,?)";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setLong(1,userId);
            ps.setLong(2,roleId);
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
    public void updateUserRole(long roleId, long userId) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Update  userrole set roleId=? where userId=?";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setLong(1,userId);
            ps.setLong(2,roleId);
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
}
