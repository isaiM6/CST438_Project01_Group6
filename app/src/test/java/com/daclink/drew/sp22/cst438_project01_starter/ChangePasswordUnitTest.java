package com.daclink.drew.sp22.cst438_project01_starter;

import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TODO: Rework tests as instrumented tests for database testing
 */

/*
 * Class: ChangePasswordUnitTest.java
 * Description: Tests for changing the passwords for the program.
 * */

public class ChangePasswordUnitTest {
    @Test
    public void getPasswordTest() {
        UserEntity user = new UserEntity("testuser", "testuser", "Test User");

        assertEquals("testuser", user.getPassword());
        assertNotEquals("usertest", user.getPassword());
    }

    // tests if user's old password is the same as their new password
    @Test
    public void validateOldPasswordTest() {
        UserEntity user = new UserEntity("testuser", "testuser", "Test User");

        String oldPassword = "testuser";

        assertEquals(user.getPassword(), oldPassword);

        oldPassword = "usertest";

        assertNotEquals(user.getPassword(), oldPassword);
    }

    // tests if user's new password is not the same as their old password
    @Test
    public void validateNewPasswordTest() {
        String newPassword = "new";
        String oldPasswrd = "old";

        assertNotEquals(newPassword, oldPasswrd);

        newPassword = "old";

        assertEquals(newPassword, oldPasswrd);
    }

    // tests if user's password was changed and saved correctly
    @Test
    public void changePasswordTest() {
        UserEntity user = new UserEntity("testuser", "testuser", "Test User");

    }
}
