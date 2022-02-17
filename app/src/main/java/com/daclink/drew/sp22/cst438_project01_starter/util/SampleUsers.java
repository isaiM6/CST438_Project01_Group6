package com.daclink.drew.sp22.cst438_project01_starter.util;

import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

import java.util.ArrayList;
import java.util.List;

/*
 * Class: SampleUsers.java
 * Description: SampleUsers just holds a list of
 * useful generic users that help developers test
 * the application's functionality.
 * */

public class SampleUsers {
    private static final UserEntity USER_1 = new UserEntity("testuser1", "testuser1", "Test User 1");
    private static final UserEntity USER_2 = new UserEntity("testuser2", "testuser2", "Test User 2");

    // retrieve sample users
    public static List<UserEntity> getUsers() {
        List<UserEntity> users = new ArrayList<>();

        users.add(USER_1);
        users.add(USER_2);

        return users;
    }
}
