package com.gitgud.hackathon;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class MySQLHelper {

    private MySQLHelper() {
    }

    ;

    public static void show_users(Connection con) throws SQLException {

        String query = "SELECT * FROM timeshare.users";
        Statement statement = null;

        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println(username + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static Connection getConnection() throws SQLException {

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timeshare?user=root");
        return connection;
    }
}
