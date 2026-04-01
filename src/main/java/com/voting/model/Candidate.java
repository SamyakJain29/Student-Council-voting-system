package com.voting.model;

import java.sql.Timestamp;

public class Candidate {
    private int id;
    private String name;
    private String department;
    private int voteCount;
    private Timestamp createdAt;

    public Candidate() {}

    public Candidate(int id, String name, String department, int voteCount, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.voteCount = voteCount;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getVoteCount() { return voteCount; }
    public void setVoteCount(int voteCount) { this.voteCount = voteCount; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
