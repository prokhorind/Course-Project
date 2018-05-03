package com.project.course.service;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * Created by kleba on 17.05.2018.
 */
public class TestDataSource {

    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/repairagency");
        ds.setUsername("root");
        ds.setPassword("15fghtkm");
        return ds;
    }
}
