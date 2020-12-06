package com.nikolenko.homeworks.homework_25;

import java.sql.*;
import java.util.Arrays;

public class ConnectionFactory {
//    private static final String host = "jdbc:mysql://localhost/";
//    private static final String dataBaseName = "world";
//    private static final String username = "mysql";
//    private static final String password = "mysql";


    private static final String host = "jdbc:mysql://mysql316.1gb.ua/";
    private static final String dataBaseName = "gbua_hillwrld";
    private static final String init = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String username = "gbua_hillwrld";
    private static final String password = "ba84d96c6xvn";

    private static Connection connection = null;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static String getHost() {
        return host + dataBaseName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    private static Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(host + dataBaseName + init, username, password);
        } catch (Exception e) {
            System.out.println("ConnectionFactory constructor error " + Arrays.toString(e.getStackTrace()));
        }
        return conn;
    }

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        connection = createConnection();
        return connection;
    }

    public static Statement getStatement() {
        if (connection == null) {
            getConnection();
        }
        if (statement != null) {
            return statement;
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("ConnectionFactory getStatement error ");
            e.printStackTrace();
            return null;
        }
        return statement;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        if (connection == null) {
            getConnection();
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("ConnectionFactory getPreparedStatement() error ");
            e.printStackTrace();
            return null;
        }
        return preparedStatement;
    }

    public static void preparedStatementClose(PreparedStatement prst){
        if(prst == null){
            return;
        }
        try {
            prst.close();
        } catch (SQLException e) {
            System.out.println("ConnectionFactory preparedStatementClose() error ");
            e.printStackTrace();
        }
    }

    public static ResultSet getResultSet(String query) {
        getConnection();
        getStatement();
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(query);
        } catch (SQLException e) {
            System.out.println("ConnectionFactory ResultSet. getResultSet()  error ");
            e.printStackTrace();
            return null;
        }
        return resultSet;
    }

    public static void updateQuery(String query) {
        getConnection();
        getStatement();
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("ConnectionFactory. updateQuery()  error ");
            e.printStackTrace();
        }
    }

    public static boolean nextResultSet(ResultSet resultSet) {
        boolean result = false;
        try {
            result = resultSet.next();
        } catch (SQLException e) {
            System.out.println("ConnectionFactory ResultSet. nextResultSet() error ");
            e.printStackTrace();
        }
        return result;
    }

    public static void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("ConnectionFactory resultSet close. close() error ");
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("ConnectionFactory statement close error ");
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("ConnectionFactory statement close error ");
                e.printStackTrace();
            }
        }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("ConnectionFactory connection close error ");
                e.printStackTrace();
            }
        connection = null;
        statement = null;
        resultSet = null;
        preparedStatement = null;
    }
}

// java.sql.SQLSyntaxErrorException: Access denied for user 'gbua_hillwrld'@'%' to database 'db'
//java.sql.SQLException: The server time zone value 'EEST' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the 'serverTimezone' configuration property) to use a more specifc time zone value if you want to utilize time zone support.