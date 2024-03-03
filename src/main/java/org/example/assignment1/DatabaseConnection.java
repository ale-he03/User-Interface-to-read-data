package org.example.assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;

    private static final String URL = "jdbc:mysql://localhost:3306/animationdb";
    private static final String USER = "root";
    private static final String PASS = "goodOmens03";
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
