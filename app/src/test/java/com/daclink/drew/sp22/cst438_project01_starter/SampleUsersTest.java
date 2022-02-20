package com.daclink.drew.sp22.cst438_project01_starter;

import org.junit.Test;
import static org.junit.Assert.*;

import com.daclink.drew.sp22.cst438_project01_starter.util.SampleUsers;

/**
 * Class: SampleUsersTest.java
 * Description: Tests the sample users class
 */
public class SampleUsersTest {
    // tests the creation of sample users and their correct credentials
    @Test
    public void getSampleUsersTest() {
        assertEquals(2, SampleUsers.getUsers().size());
        assertEquals("testuser1", SampleUsers.getUsers().get(0).getUsername());
        assertEquals("testuser1", SampleUsers.getUsers().get(0).getPassword());
        assertEquals("Test User 1", SampleUsers.getUsers().get(0).getName());
        assertEquals("testuser2", SampleUsers.getUsers().get(1).getUsername());
        assertEquals("testuser2", SampleUsers.getUsers().get(1).getPassword());
        assertEquals("Test User 2", SampleUsers.getUsers().get(1).getName());
    }
}
