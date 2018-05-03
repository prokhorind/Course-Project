package com.project.course.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper {
    private Connection connection;
    private boolean isTransaction;

    public ConnectionWrapper(Connection connection, boolean isTransaction){
        this.connection = connection;
        this.isTransaction = isTransaction;
    }

    public PreparedStatement createPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    Connection getConnection(){
        return connection;
    }

    public void close() throws SQLException{
        if(!isTransaction){
               connection.close();
        }
    }

}
