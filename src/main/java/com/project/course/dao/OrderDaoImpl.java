package com.project.course.dao;

import com.project.course.entity.Order;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.transaction.ConnectionWrapper;
import com.project.course.transaction.TransactionUtil;
import com.project.course.util.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kleba on 03.05.2018.
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public void addOrder(Order order) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Insert into Orders(date,status,userId) values(?,?,?)";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setDate(1,order.getDate());
            ps.setString(2,order.getStatus());
            ps.setLong(3,order.getUserId());
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
    public void deleteOrder(long id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="DELETE FROM Orders where orderId=?";
        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
    }

    @Override
    public void updateOrderStatus(long id, String status) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="Update  orders set status=? where orderId=?";
        try {
            PreparedStatement ps= con.createPreparedStatement(sql);
            ps.setString(1,status);
            ps.setLong(2,id);
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
    public Set<Order> getOrders(long from,  long numberOfOrders) throws DataBaseException, DAOException {
        Set<Order> orderSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT * FROM Orders LIMIT ? OFFSET ?;";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,numberOfOrders);
            preparedStatement.setLong(2,from);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                Order order =new Order();
                order.setOrderId(rs.getLong(1));
                order.setDate(rs.getDate(2));
                order.setStatus(rs.getString(3));
                order.setUserId(rs.getLong(4));
                orderSet.add(order);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return orderSet;
    }

    @Override
    public Set<Order> getOrders(long from, long numberOfOrders, OrderStatus orderStatus) throws DataBaseException, DAOException {
        Set<Order> orderSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT * FROM Orders WHERE status= ? LIMIT ? OFFSET ?;";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setString(1,orderStatus.toString());
            preparedStatement.setLong(2,numberOfOrders);
            preparedStatement.setLong(3,from);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                Order order =new Order();
                order.setOrderId(rs.getLong(1));
                order.setDate(rs.getDate(2));
                order.setStatus(rs.getString(3));
                order.setUserId(rs.getLong(4));
                orderSet.add(order);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return orderSet;
    }

    @Override
    public Set<Order> getOrders(long from, long numberOfOrders,long userId) throws DataBaseException, DAOException {
        Set<Order> orderSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT * FROM Orders WHERE userId= ? LIMIT ? OFFSET ?;";

        try {
            PreparedStatement preparedStatement= con.createPreparedStatement(sql);
            preparedStatement.setLong(1,userId);
            preparedStatement.setLong(2,numberOfOrders);
            preparedStatement.setLong(3,from);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                Order order =new Order();
                order.setOrderId(rs.getLong(1));
                order.setDate(rs.getDate(2));
                order.setStatus(rs.getString(3));
                order.setUserId(rs.getLong(4));
                orderSet.add(order);
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return orderSet;
    }

    @Override
    public long countOrders() throws DataBaseException, DAOException {
        long number;
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT COUNT(orderId)FROM Orders";
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = con.createPreparedStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()){
                number=rs.getLong(1);
            }else{
                throw new  SQLException();
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return number;
    }

    @Override
    public long countOrders(long userId) throws DataBaseException, DAOException {
        long number;
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT COUNT(orderId)FROM Orders Where userId=?";
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1,userId);
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()){
                number=rs.getLong(1);
            }else{
                throw new  SQLException();
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }

        return number;
    }

    @Override
    public long countOrders(OrderStatus orderStatus) throws DataBaseException, DAOException {
        long number;
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT COUNT(orderId)FROM Orders Where status=?";
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setString(1,orderStatus.toString());
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()){
                number=rs.getLong(1);
            }else{
                throw new  SQLException();
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }

        return number;
    }

    @Override
    public long getLastOrder() throws DataBaseException, DAOException {
        long id;
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql ="SELECT MAX(orderId)FROM Orders";
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = con.createPreparedStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()){
                id=rs.getLong(1);
            }else{
                throw new  SQLException();
            }
        } catch (SQLException e) {
            throw  new DAOException(e);
        }
        return id;
    }
}
