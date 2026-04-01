package com.voting.repository;

import com.voting.model.Candidate;
import com.voting.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateRepository {

    public boolean addCandidate(Candidate candidate) {
        String query = "INSERT INTO candidates (name, department) VALUES (?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getDepartment());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCandidate(Candidate candidate) {
        String query = "UPDATE candidates SET name = ?, department = ? WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getDepartment());
            stmt.setInt(3, candidate.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCandidate(int candidateId) {
        String query = "DELETE FROM candidates WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, candidateId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";
        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                candidates.add(extractCandidateFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public boolean incrementVoteCount(int candidateId) {
        String query = "UPDATE candidates SET vote_count = vote_count + 1 WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, candidateId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Candidate extractCandidateFromResultSet(ResultSet rs) throws SQLException {
        return new Candidate(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getInt("vote_count"),
                rs.getTimestamp("created_at")
        );
    }
}
