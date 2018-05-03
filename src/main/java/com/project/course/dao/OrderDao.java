package com.project.course.dao;

import com.project.course.entity.Order;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

import java.util.Set;

/**
 * Created by kleba on 03.05.2018.
 */
public interface OrderDao {
    void addOrder(Order order) throws DAOException, DataBaseException;
    void deleteOrder(long id) throws DAOException, DataBaseException;
    void updateOrderStatus(long id,String status)throws DAOException, DataBaseException;
    Set<Order> getOrders(long from, long to) throws DataBaseException, DAOException;
}
