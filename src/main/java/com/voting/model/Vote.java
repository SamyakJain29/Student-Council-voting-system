package com.voting.model;

import java.sql.Timestamp;

public class Vote {
    private int id;
    private int userId;
    private int candidateId;
    private String ipAddress;
    private String systemInfo;
    private Timestamp timestamp;

    public Vote() {}

    public Vote(int id, int userId, int candidateId, String ipAddress, String systemInfo, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.candidateId = candidateId;
        this.ipAddress = ipAddress;
        this.systemInfo = systemInfo;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCandidateId() { return candidateId; }
    public void setCandidateId(int candidateId) { this.candidateId = candidateId; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public String getSystemInfo() { return systemInfo; }
    public void setSystemInfo(String systemInfo) { this.systemInfo = systemInfo; }
    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
