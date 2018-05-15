package com.project.course.dao;

import com.project.course.entity.User;
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
public class UserDaoImpl implements UserDao {
    @Override
    public void insertUser(User user) throws DAOException, DataBaseException{
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Insert into user(name,surname,login,password,email) values(?,?,?,?,?)";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getSurname());
            ps.setString(3,user.getLogin());
            ps.setString(4,user.getPass());
            ps.setString(5,user.getEmail());
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
    public User getUserById(long id)throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select * from user where userId=?";
        User user = null;
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setLogin(rs.getString(4));
                user.setPass(rs.getString(5));
                user.setEmail(rs.getString(6));
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return user;
    }

    @Override
    public void deleteUser(long id)throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="DELETE FROM user where userId=?";
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
    }

    @Override
    public long getUserId(String login) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select userId from user where login=?";
        long userId=0;
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setString(1,login);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                userId=rs.getLong(1);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return userId;
    }

    @Override
    public Set<User> getUsers(long from, long to) throws DAOException, DataBaseException {
        Set<User> userSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT column FROM table LIMIT ? OFFSET ?;";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,to);
            preparedStatement.setLong(2,from);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setLogin(rs.getString(4));
                user.setPass(rs.getString(5));
                user.setEmail(rs.getString(6));
                userSet.add(user);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return userSet;
    }

    @Override
    public long getUserId(String login, String password) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Select userId from user where login=? and password=?";
        long userId=0;
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                userId=rs.getLong(1);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return userId;
    }
}
