package com.gitgud.hackathon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public final class Database {

    private Database() {}

    public static Boolean register_user(Connection con, HashMap<String,String> form) throws SQLException {

        String username = form.get("username");
        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        String email = form.get("email");
        String password = form.get("password");

        String query = "INSERT INTO users (first_name,last_name,email,password) VALUES ()";
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
