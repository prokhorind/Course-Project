package com.project.course.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by kleba on 30.04.2018.
 */
public class ConnectionPool {

    private static volatile DataSource dataSource;
    private static ConnectionPool pool;
    private ConnectionPool(){};

    public static synchronized Connection getConnection(){
        InitialContext context = null;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/RepairAgencyDB");
            return dataSource.getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }

    }
    public static synchronized ConnectionPool getInstance() throws NamingException{
        if(pool == null){
            pool =new ConnectionPool();
        }
        return pool;

    }
}
