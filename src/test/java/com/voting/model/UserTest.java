package com.voting.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Timestamp;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "John Doe", "12345", "password", "STUDENT", false, timestamp);

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("12345", user.getStudentId());
        assertEquals("password", user.getPassword());
        assertEquals("STUDENT", user.getRole());
        assertFalse(user.hasVoted());
        assertEquals(timestamp, user.getCreatedAt());
    }

    @Test
    public void testSetters() {
        User user = new User();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        user.setId(2);
        user.setName("Jane Doe");
        user.setStudentId("67890");
        user.setPassword("newpassword");
        user.setRole("ADMIN");
        user.setHasVoted(true);
        user.setCreatedAt(timestamp);

        assertEquals(2, user.getId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("67890", user.getStudentId());
        assertEquals("newpassword", user.getPassword());
        assertEquals("ADMIN", user.getRole());
        assertTrue(user.hasVoted());
        assertEquals(timestamp, user.getCreatedAt());
    }

    @Test
    public void testDefaultConstructor() {
        User user = new User();

        assertEquals(0, user.getId());
        assertNull(user.getName());
        assertNull(user.getStudentId());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertFalse(user.hasVoted());
        assertNull(user.getCreatedAt());
    }
}