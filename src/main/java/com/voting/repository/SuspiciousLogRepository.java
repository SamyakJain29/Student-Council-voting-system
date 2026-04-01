package com.voting.repository;

import com.voting.model.SuspiciousLog;
import com.voting.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuspiciousLogRepository {

    public boolean logSuspiciousActivity(int userId, String reason) {
        String query = "INSERT INTO suspicious_logs (user_id, reason) VALUES (?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            if (userId > 0) {
                stmt.setInt(1, userId);
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }
            stmt.setString(2, reason);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
