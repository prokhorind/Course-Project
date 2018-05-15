package com.project.course.dao;

import com.project.course.entity.Order;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.util.OrderStatus;

import java.util.Set;

/**
 * Created by kleba on 03.05.2018.
 */
public interface OrderDao {
    void addOrder(Order order) throws DAOException, DataBaseException;
    void deleteOrder(long id) throws DAOException, DataBaseException;
    void updateOrderStatus(long id,String status)throws DAOException, DataBaseException;
    Set<Order> getOrders(long from, long numberOfOrders) throws DataBaseException, DAOException;
    Set<Order> getOrders(long from, long numberOfOrders, OrderStatus orderStatus) throws DataBaseException, DAOException;
    Set<Order> getOrders(long from, long numberOfOrders,long userId) throws DataBaseException, DAOException;
    long getUserIdByOrderId(long orderId)throws DataBaseException, DAOException;
    long countOrders()throws DataBaseException, DAOException;
    long countOrders(long userId)throws DataBaseException, DAOException;
    long countOrders(OrderStatus orderStatus) throws DataBaseException, DAOException;
    long getLastOrder()throws DataBaseException, DAOException;
}
