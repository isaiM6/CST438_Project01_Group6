package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Class: UserDaoTest.java
 * Description: Basic tests for the user DAO
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void insertUserTest() {
        AppDatabase db = AppDatabase.getInstance(context);
        UserDao userDao = db.userDao();

        int currentTableSize = userDao.getAllUsers().size();

        UserEntity user = new UserEntity("testuser", "testuser", "Test User");
        userDao.insertUser(user);

        assertEquals(userDao.getAllUsers().size(), currentTableSize + 1);

        assertNotNull(userDao.getUserByUsername("testuser").getUsername());
        assertNotNull(userDao.getUserByUsername("testuser").getPassword());

        user = userDao.getUserByUsername("testuser");

        userDao.delete(user);
    }

    @Test
    public void updateUserTest() {
        AppDatabase db = AppDatabase.getInstance(context);
        UserDao userDao = db.userDao();

        UserEntity user = new UserEntity("testuser", "testuser", "Test User");
        userDao.insertUser(user);

        user = userDao.getUserByUsername("testuser");

        user.setUsername("otheruser");
        user.setPassword("otheruser");
        userDao.updateUser(user);

        assertNotEquals(userDao.getUserByUsername("testuser"), user);
        assertNotEquals(userDao.getUserByUsername("otherUser"), user);

        userDao.delete(user);
    }

    @Test
    public void deleteUserTest() {
        AppDatabase db = AppDatabase.getInstance(context);
        UserDao userDao = db.userDao();

        int currentTableSize = userDao.getAllUsers().size();

        UserEntity user = new UserEntity("testuser", "testuser", "Test User");
        userDao.insertUser(user);

        assertEquals(userDao.getAllUsers().size(), currentTableSize + 1);

        user = userDao.getUserByUsername("testuser");

        userDao.delete(user);

        assertEquals(userDao.getAllUsers().size(), currentTableSize);
    }

    @Test
    public void getUserByUserId() {
        AppDatabase db = AppDatabase.getInstance(context);
        UserDao userDao = db.userDao();

        assertNull(userDao.getUserById(-2));

        UserEntity user = new UserEntity("testuser", "testuser", "Test User");
        user.setUserId(-2);
        userDao.insertUser(user);

        assertNotNull(userDao.getUserById(-2));

        user = userDao.getUserById(-2);
        assertEquals(user.getUsername(), "testuser");
        assertEquals(user.getPassword(), "testuser");

        userDao.delete(user);
    }

    @Test
    public void getUserByUsername() {
        AppDatabase db = AppDatabase.getInstance(context);
        UserDao userDao = db.userDao();

        assertNull(userDao.getUserByUsername("testuser"));

        UserEntity user = new UserEntity("testuser", "testuser", "Test User");
        userDao.insertUser(user);

        assertNotNull(userDao.getUserByUsername("testuser"));

        user = userDao.getUserByUsername("testuser");
        assertEquals(user.getUsername(), "testuser");
        assertEquals(user.getPassword(), "testuser");

        userDao.delete(user);
    }

    @Test
    public void getAllUsersTest() {
        AppDatabase db = AppDatabase.getInstance(context);
        UserDao userDao = db.userDao();
        List<UserEntity> users = null;

        assertNotEquals(userDao.getAllUsers(), users);
        assertNull(users);

        UserEntity user1 = new UserEntity("testuser1", "testuser1", "Test User 1");
        UserEntity user2 = new UserEntity("testuser2", "testuser2", "Test User 2");
        UserEntity user3 = new UserEntity("testuser3", "testuser3", "Test User 3");

        userDao.insertUser(user1, user2, user3);
        users = userDao.getAllUsers();

        assertEquals(users.size(), userDao.getAllUsers().size());
        assertNotNull(users);

        userDao.delete(user1, user2, user3);
    }
}
