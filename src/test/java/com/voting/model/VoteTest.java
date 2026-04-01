package com.voting.model;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class VoteTest {

    @Test
    public void testVoteConstructorAndGetters() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Vote vote = new Vote(1, 100, 200, "192.168.1.1", "Windows 10", timestamp);

        assertEquals(1, vote.getId());
        assertEquals(100, vote.getUserId());
        assertEquals(200, vote.getCandidateId());
        assertEquals("192.168.1.1", vote.getIpAddress());
        assertEquals("Windows 10", vote.getSystemInfo());
        assertEquals(timestamp, vote.getTimestamp());
    }

    @Test
    public void testSetters() {
        Vote vote = new Vote();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        vote.setId(2);
        vote.setUserId(101);
        vote.setCandidateId(201);
        vote.setIpAddress("10.0.0.1");
        vote.setSystemInfo("Linux");
        vote.setTimestamp(timestamp);

        assertEquals(2, vote.getId());
        assertEquals(101, vote.getUserId());
        assertEquals(201, vote.getCandidateId());
        assertEquals("10.0.0.1", vote.getIpAddress());
        assertEquals("Linux", vote.getSystemInfo());
        assertEquals(timestamp, vote.getTimestamp());
    }

    @Test
    public void testDefaultConstructor() {
        Vote vote = new Vote();

        assertEquals(0, vote.getId());
        assertEquals(0, vote.getUserId());
        assertEquals(0, vote.getCandidateId());
        assertNull(vote.getIpAddress());
        assertNull(vote.getSystemInfo());
        assertNull(vote.getTimestamp());
    }
}