package com.daclink.drew.sp22.cst438_project01_starter.util;

import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class SampleUsers {
    private static final UserEntity USER_1 = new UserEntity("testuser1", "testuser1", "Test User 1");
    private static final UserEntity USER_2 = new UserEntity("testuser2", "testuser2", "Test User 2");

    public static List<UserEntity> getUsers() {
        List<UserEntity> users = new ArrayList<>();

        users.add(USER_1);
        users.add(USER_2);

        return users;
    }
}
