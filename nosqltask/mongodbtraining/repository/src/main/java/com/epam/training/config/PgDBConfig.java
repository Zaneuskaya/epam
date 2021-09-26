package com.epam.training.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgDBConfig {

    private static String url = "jdbc:postgresql://localhost:5432/epam_training";
    private static String user = "postgres";
    private static String password = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
