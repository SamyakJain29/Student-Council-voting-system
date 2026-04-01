package com.voting.model;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class CandidateTest {

    @Test
    public void testCandidateConstructorAndGetters() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Candidate candidate = new Candidate(1, "Alice Johnson", "Computer Science", 10, timestamp);

        assertEquals(1, candidate.getId());
        assertEquals("Alice Johnson", candidate.getName());
        assertEquals("Computer Science", candidate.getDepartment());
        assertEquals(10, candidate.getVoteCount());
        assertEquals(timestamp, candidate.getCreatedAt());
    }

    @Test
    public void testSetters() {
        Candidate candidate = new Candidate();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        candidate.setId(2);
        candidate.setName("Bob Smith");
        candidate.setDepartment("Mathematics");
        candidate.setVoteCount(5);
        candidate.setCreatedAt(timestamp);

        assertEquals(2, candidate.getId());
        assertEquals("Bob Smith", candidate.getName());
        assertEquals("Mathematics", candidate.getDepartment());
        assertEquals(5, candidate.getVoteCount());
        assertEquals(timestamp, candidate.getCreatedAt());
    }

    @Test
    public void testDefaultConstructor() {
        Candidate candidate = new Candidate();

        assertEquals(0, candidate.getId());
        assertNull(candidate.getName());
        assertNull(candidate.getDepartment());
        assertEquals(0, candidate.getVoteCount());
        assertNull(candidate.getCreatedAt());
    }
}