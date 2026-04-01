package com.voting.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    // H2 embedded database for development/testing
    private static final String URL = "jdbc:h2:~/student_council_voting;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = ""; // H2 default

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "student_id VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "role VARCHAR(20) NOT NULL DEFAULT 'STUDENT', " +
                    "has_voted BOOLEAN DEFAULT FALSE, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            stmt.execute(userTable);

            String candidateTable = "CREATE TABLE IF NOT EXISTS candidates (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "department VARCHAR(100) NOT NULL, " +
                    "vote_count INT DEFAULT 0, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            stmt.execute(candidateTable);

            String voteTable = "CREATE TABLE IF NOT EXISTS votes (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "candidate_id INT NOT NULL, " +
                    "ip_address VARCHAR(45), " +
                    "system_info VARCHAR(255), " +
                    "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (candidate_id) REFERENCES candidates(id) ON DELETE CASCADE)";
            stmt.execute(voteTable);

            String suspiciousLogsTable = "CREATE TABLE IF NOT EXISTS suspicious_logs (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "user_id INT, " +
                    "reason VARCHAR(255) NOT NULL, " +
                    "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL)";
            stmt.execute(suspiciousLogsTable);

            // Default admin user (password 'admin123' is hashed using SHA-256)
            String adminUser = "MERGE INTO users (name, student_id, password, role) KEY(student_id) " +
                    "VALUES ('System Admin', 'admin', 'JAvlGPq9JyTdtvBO6x2llnRI1+gxwIyPqCKAn3THIKk=', 'ADMIN')";
            stmt.execute(adminUser);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize database: " + e.getMessage());
        }
    }
}
