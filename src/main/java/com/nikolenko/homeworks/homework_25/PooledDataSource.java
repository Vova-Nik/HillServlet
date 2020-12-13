package com.nikolenko.homeworks.homework_25;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;


public class PooledDataSource {
//    private static final String host = "jdbc:mysql://localhost/";
//    private static final String dataBaseName = "world";
//    private static final String username = "mysql";
//    private static final String password = "mysql";
//    private static java.util.Date creationTime;

    private static final String host = "jdbc:mysql://mysql316.1gb.ua/";
    private static final String dataBaseName = "gbua_hillwrld";
    private static final String init = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String username = "gbua_hillwrld";
    private static final String password = "ba84d96c6xvn";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";


    private static final BasicDataSource dataSource;
    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUrl(host + dataBaseName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setConnectionProperties("useJDBCCompliantTimezoneShift=true;useLegacyDatetimeCode=false;serverTimezone=UTC");

        dataSource.setMinIdle(1);
        dataSource.setMaxIdle(4);
        dataSource.setMaxOpenPreparedStatements(16);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
