package by.lamaka.library.dao.connection.impl;

import by.lamaka.library.dao.connection.ConnectionDB;

import java.sql.*;

public class ConnectionDBImpl implements ConnectionDB {
    private Connection connection;
    private Statement statement;
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/freeit_library?serverTimezone=Europe/Minsk";

    public ConnectionDBImpl() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }


    @Override
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public void closeStatement() throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }


}
