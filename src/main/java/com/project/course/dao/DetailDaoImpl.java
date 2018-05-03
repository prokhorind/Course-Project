package com.project.course.dao;

import com.project.course.entity.Detail;
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
public class DetailDaoImpl implements DetailDao {
    @Override
    public void addDetail(Detail detail) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "Insert into details(name,reason,orderId) values(?,?,?)";
        try {
            PreparedStatement ps = con.createPreparedStatement(sql);
            ps.setString(1, detail.getName());
            ps.setString(2,detail.getReason());
            ps.setLong(3,detail.getOrderId());
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
    public void deleteDetail(long id) throws DAOException, DataBaseException {
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "DELETE FROM details where detailId=?";
        try {
            PreparedStatement preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Set<Detail> getDetails(long from, long to) throws DataBaseException, DAOException {
        Set<Detail>  detailSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "Select * from details LIMIT ? OFFSET ?";

        try {
            PreparedStatement preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1, to);
            preparedStatement.setLong(2, from);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Detail detail = new Detail(rs.getLong(1), rs.getString(2),rs.getString(3),rs.getLong(4));
                detailSet.add(detail);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return detailSet;
    }

    @Override
    public Set<Detail> getDetails(long orderId) throws DataBaseException, DAOException {
        Set<Detail>  detailSet = new HashSet<>();
        ConnectionWrapper con = TransactionUtil.getConnection();
        String sql = "Select * from details where orderId = ?";
        try {
            PreparedStatement preparedStatement = con.createPreparedStatement(sql);
            preparedStatement.setLong(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Detail detail = new Detail(rs.getLong(1), rs.getString(2),rs.getString(3),rs.getLong(4));
                detailSet.add(detail);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return detailSet;
    }


}

