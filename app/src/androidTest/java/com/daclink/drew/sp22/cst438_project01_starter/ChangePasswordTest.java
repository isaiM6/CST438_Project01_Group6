package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.Constants;

/**
 * Class: ChangePasswordTest.java
 * Description: Instrumented tests for ChangePasswordTest.java
 */
@RunWith(AndroidJUnit4.class)
public class ChangePasswordTest {
    private AppDatabase mDb;
    private UserDao mDao;
    private UserEntity mTestUser = new UserEntity("testuser", "testuser", "Test User");

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.userDao();

        Log.i("ChangePasswordTest", "created DB");
    }

    @After
    public void closeDb() {
        mDb.close();

        Log.i("ChangePasswordTest", "closeDb: ");
    }

    // test that user's password can be retrieved
    @Test
    public void getPasswordTest() {
        String correctPassword = "testuser";
        String incorrectPassword = "usertest";

        assertEquals(correctPassword, mTestUser.getPassword());
        assertNotEquals(incorrectPassword, mTestUser.getPassword());
    }

    // tests if user's old password is the same as their new password
    @Test
    public void validateOldPasswordTest() {
        String password = "testuser";

        assertTrue(mTestUser.getPassword().equals(password));

        password = "usertest";

        assertTrue(!mTestUser.getPassword().equals(password));

    }

    // tests if user's new password is not the same as their old password
    @Test
    public void validateNewPasswordTest() {
        String newPassword = "usertest";

        assertTrue(!mTestUser.getPassword().equals(newPassword));

        newPassword = "testuser";

        assertTrue(mTestUser.getPassword().equals(newPassword));
    }

    // tests the logic of the changePassword method
    @Test
    public void changePasswordTest() {
        mDao.insertUser(mTestUser);
        UserEntity user = mDao.getUserByUsername("testuser");

        assertNotNull(user);
        assertTrue(user.getPassword().equals("testuser"));
        assertTrue(!user.getPassword().equals("usertest"));

        AlertDialog.Builder alertBuilder = null;
        assertNull(alertBuilder);

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        alertBuilder = new AlertDialog.Builder(context, R.style.Base_Theme_AppCompat_Dialog_Alert);
        assertNotNull(alertBuilder);

        assertEquals("testuser", mDao.getUserByUsername("testuser").getPassword());

        String newPassword = "usertest";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());

        mTestUser = user;
        mDao.updateUser(mTestUser);
        assertNotEquals("testuser", mDao.getUserByUsername("testuser").getPassword());
        assertEquals(newPassword, mDao.getUserByUsername("testuser").getPassword());
    }

    // tests creation of ChangePasswordActivity intent
    @Test
    public void changePasswordIntentTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = null;
        int defaultValue = -1;

        assertNull(intent);
        intent = new Intent(context, MovieDetailsActivity.class);

        assertNotNull(intent);
        assertEquals(defaultValue, intent.getIntExtra(Constants.USER_ID_KEY, defaultValue));

        mTestUser.setUserId(1);
        assertEquals(1, mTestUser.getUserId());

        intent.putExtra(Constants.USER_ID_KEY, mTestUser.getUserId());
        assertNotEquals(defaultValue, intent.getIntExtra(Constants.USER_ID_KEY, defaultValue));;
        assertEquals(mTestUser.getUserId(), intent.getIntExtra(Constants.USER_ID_KEY, defaultValue));
    }
}
