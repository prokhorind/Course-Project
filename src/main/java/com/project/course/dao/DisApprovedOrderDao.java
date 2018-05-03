package com.project.course.dao;

import com.project.course.entity.DisApprovedOrder;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;

import java.util.Set;

/**
 * Created by kleba on 03.05.2018.
 */
public interface DisApprovedOrderDao {
    void addDisApprovedOrder(DisApprovedOrder disapprovedOrder) throws DAOException, DataBaseException;
    void deleteDisApprovedOrder(long id) throws DAOException, DataBaseException;
    Set<DisApprovedOrder> getDisApprovedOrders(long from, long to) throws DataBaseException, DAOException;
}
