package com.gitgud.hackathon.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class MySQLHelper {

    private MySQLHelper() {}

    public static Connection getConnection() throws SQLException {

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timeshare?user=root");
        return connection;
    }
}
