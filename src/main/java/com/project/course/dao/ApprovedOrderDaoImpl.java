package com.project.course.dao;

import com.project.course.entity.ApprovedOrder;
import com.project.course.exception.DAOException;
import com.project.course.exception.DataBaseException;
import com.project.course.transaction.ConnectionWrapper;
import com.project.course.transaction.TransactionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kleba on 03.05.2018.
 */
public class ApprovedOrderDaoImpl implements ApprovedOrderDao {

    @Override
    public void addApprovedOrder(ApprovedOrder approvedOrder) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "Insert into approvedorder(aoId,price) values(?,?)";
        try {
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setLong(1,approvedOrder.getOrderId());
            ps.setString(2, String.valueOf(approvedOrder.getPrice()));
            ps.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }
    }

    @Override
    public void deleteApprovedOrder(long id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "DELETE FROM approvedorder where aoId=?";
        try {
            PreparedStatement preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Set<ApprovedOrder> getApprovedOrders(long from, long to) throws DataBaseException, DAOException {
        Set<ApprovedOrder> approvedOrderSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "Select * from approvedorder LIMIT ? OFFSET ?";

        try {
            PreparedStatement preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1, to);
            preparedStatement.setLong(2, from);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ApprovedOrder approvedOrder = new ApprovedOrder(rs.getLong(1), rs.getBigDecimal(2));
                approvedOrderSet.add(approvedOrder);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return approvedOrderSet;
    }
}
