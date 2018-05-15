package com.project.course.dao;

import com.project.course.entity.DisApprovedOrder;
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
 * Created by kleba on 03.05.2018.
 */
public class DisApprovedOrderDaoImpl implements DisApprovedOrderDao {
    @Override
    public void addDisApprovedOrder(DisApprovedOrder disapprovedOrder) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "Insert into disapprovedorder(daoId,reason) values(?,?)";
        try {
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setLong(1,disapprovedOrder.getOrderId());
            ps.setString(2, disapprovedOrder.getReason());
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
    public void deleteDisApprovedOrder(long id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "DELETE FROM disapprovedorder where daoId=?";
        try {
            PreparedStatement preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Set<DisApprovedOrder> getDisApprovedOrders(long from, long to) throws DataBaseException, DAOException {
        Set<DisApprovedOrder> disApprovedOrderSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "Select * from disapprovedorder LIMIT ? OFFSET ?";

        try {
            PreparedStatement preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1, to);
            preparedStatement.setLong(2, from);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DisApprovedOrder disApprovedOrder = new DisApprovedOrder(rs.getLong(1), rs.getString(2));
                disApprovedOrderSet.add(disApprovedOrder);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return disApprovedOrderSet;
    }
}
