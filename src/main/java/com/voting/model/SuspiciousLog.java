package com.voting.model;

import java.sql.Timestamp;

public class SuspiciousLog {
    private int id;
    private int userId;
    private String reason;
    private Timestamp timestamp;

    public SuspiciousLog() {}

    public SuspiciousLog(int id, int userId, String reason, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
