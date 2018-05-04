package com.project.course.dao;

import com.project.course.entity.ApprovedOrder;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

import java.util.List;
import java.util.Set;

/**
 * Created by kleba on 03.05.2018.
 */
public interface ApprovedOrderDao {
    void addApprovedOrder(ApprovedOrder approvedOrder) throws DAOException, DataBaseException;
    void deleteApprovedOrder(long id) throws DAOException, DataBaseException;
    Set<ApprovedOrder> getApprovedOrders(long from, long to) throws DataBaseException, DAOException;
}
