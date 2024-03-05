package com.example.skylink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestUtil {

    private static final String JDBC_URL = "jdbc:hsqldb:mem:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    // This method can be used to initialize the database connection before tests
    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    // Your integration tests here
}
