package com.gitgud.hackathon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class Database {

    /* Factory database class, performs connections and queries the database.  */

    private Database() {}

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
}
