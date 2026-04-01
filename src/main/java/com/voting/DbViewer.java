package com.voting;

import com.voting.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DbViewer {
    public static void main(String[] args) {
        String[] tables = {"users", "candidates", "votes", "suspicious_logs"};
        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement()) {
            
            for (String table : tables) {
                System.out.println("=== Table: " + table + " ===");
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rsmd.getColumnName(i) + "\t|\t");
                    }
                    System.out.println();
                    System.out.println("-".repeat(50));
                    
                    while (rs.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(rs.getString(i) + "\t|\t");
                        }
                        System.out.println();
                    }
                } catch (Exception e) {
                    System.out.println("Error reading table " + table + ": " + e.getMessage());
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
