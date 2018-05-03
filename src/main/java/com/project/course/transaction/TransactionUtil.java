package com.project.course.transaction;


import com.project.course.exception.DataBaseException;
import com.project.course.util.ConnectionPool;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionUtil {
    private static final ThreadLocal<ConnectionWrapper> connections = new ThreadLocal<>();

    private TransactionUtil() {
    }

    public static void beginTransaction() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if (Objects.nonNull(con)) {
            throw new DataBaseException();
        }
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            con = new ConnectionWrapper(connection, true);
            connections.set(con);
        } catch (NamingException e) {
            throw new DataBaseException(e);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    public static void endTransaction() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if (Objects.isNull(con)) {
            throw new DataBaseException();
        }

        try {
            con.getConnection().close();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        connections.set(null);

    }

    public static void rollback() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if (Objects.isNull(con)) {
            throw new DataBaseException();
        }

        try {
            con.getConnection().rollback();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    public static void commit() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if (Objects.isNull(con)) {
            throw new DataBaseException();
        }

        try {
            con.getConnection().commit();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }


    public static ConnectionWrapper getConnection() throws DataBaseException {
        ConnectionWrapper con = connections.get();
        if (Objects.nonNull(con)) {
            return con;
        }
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(true);
            con = new ConnectionWrapper(connection, false);
            return con;
        } catch (NamingException e) {
            throw new DataBaseException(e);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }

    }
}
