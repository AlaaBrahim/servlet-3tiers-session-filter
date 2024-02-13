package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection connect() throws SQLException {

        try {
            // Get database credentials from DatabaseConfig class
            String jdbcUrl = DatabaseConfig.getDbUrl();
            String user = DatabaseConfig.getDbUsername();
            String password = DatabaseConfig.getDbPassword();

            // Open a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            // Create users table if not exists
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50) , email VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(200) NOT NULL)");

            // Create sales table if not exists
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS sales (id SERIAL PRIMARY KEY, product VARCHAR(50), quantity INTEGER, amount FLOAT, date DATE)");

            return connection;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
