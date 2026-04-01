package com.voting.model;

import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private String studentId;
    private String password;
    private String role;
    private boolean hasVoted;
    private Timestamp createdAt;

    public User() {}

    public User(int id, String name, String studentId, String password, String role, boolean hasVoted, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.role = role;
        this.hasVoted = hasVoted;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean hasVoted() { return hasVoted; }
    public void setHasVoted(boolean hasVoted) { this.hasVoted = hasVoted; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
