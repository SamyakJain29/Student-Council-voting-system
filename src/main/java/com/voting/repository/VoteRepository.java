package com.voting.repository;

import com.voting.model.Vote;
import com.voting.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteRepository {

    public boolean castVote(Vote vote) {
        String query = "INSERT INTO votes (user_id, candidate_id, ip_address, system_info) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, vote.getUserId());
            stmt.setInt(2, vote.getCandidateId());
            stmt.setString(3, vote.getIpAddress());
            stmt.setString(4, vote.getSystemInfo());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasUserVoted(int userId) {
        String query = "SELECT COUNT(*) FROM votes WHERE user_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Vote> getRecentVotes(int limit) {
        List<Vote> votes = new ArrayList<>();
        String query = "SELECT * FROM votes ORDER BY timestamp DESC LIMIT ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                votes.add(new Vote(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("candidate_id"),
                        rs.getString("ip_address"),
                        rs.getString("system_info"),
                        rs.getTimestamp("timestamp")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return votes;
    }

    public List<Vote> getVotesByIpAddress(String ipAddress) {
        List<Vote> votes = new ArrayList<>();
        String query = "SELECT * FROM votes WHERE ip_address = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, ipAddress);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                votes.add(new Vote(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("candidate_id"),
                        rs.getString("ip_address"),
                        rs.getString("system_info"),
                        rs.getTimestamp("timestamp")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return votes;
    }
}
