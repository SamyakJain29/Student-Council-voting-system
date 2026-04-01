package com.voting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.voting.model.User;
import com.voting.repository.UserRepository;
import com.voting.util.DatabaseUtils;

public class DatabaseCheck {
    public static void main(String[] args) {
        try {
            // Initialize database
            DatabaseUtils.initializeDatabase();

            // Check if admin user exists
            UserRepository userRepo = new UserRepository();
            User admin = userRepo.getUserByStudentId("admin");

            if (admin != null) {
                System.out.println("Admin user found:");
                System.out.println("Name: " + admin.getName());
                System.out.println("Student ID: " + admin.getStudentId());
                System.out.println("Role: " + admin.getRole());
                System.out.println("Has Voted: " + admin.hasVoted());
            } else {
                System.out.println("Admin user NOT found!");
            }

            // Also check directly with SQL
            checkUsersTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkUsersTable() {
        String sql = "SELECT id, name, student_id, role, has_voted FROM users";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nAll users in database:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Student ID: " + rs.getString("student_id") +
                        ", Role: " + rs.getString("role") +
                        ", Has Voted: " + rs.getBoolean("has_voted"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}