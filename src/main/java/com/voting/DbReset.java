package com.voting;

import com.voting.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.Statement;

public class DbReset {
    public static void main(String[] args) {
        try {
            System.out.println("Connecting to the database...");
            try (Connection conn = DatabaseUtils.getConnection();
                 Statement stmt = conn.createStatement()) {
                
                System.out.println("Dropping all existing tables...");
                stmt.execute("DROP ALL OBJECTS;");
                System.out.println("Successfully removed all data.");
            }

            System.out.println("Re-initializing the database schema and default admin...");
            DatabaseUtils.initializeDatabase();
            System.out.println("Database reset complete. Only default admin credentials now exist.");
            
        } catch (Exception e) {
            System.out.println("Error resetting database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
