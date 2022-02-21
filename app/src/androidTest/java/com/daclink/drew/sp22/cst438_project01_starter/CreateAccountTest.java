package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.Constants;

/**
 * Class: CreateAccountTest.java
 * Description: Instrumented test for CreateAccountTest.java
 */

@RunWith(AndroidJUnit4.class)
public class CreateAccountTest {
    private AppDatabase mDb;
    private UserDao mDao;
    private UserEntity mTestUser = new UserEntity("user999", "testuser", "Test User");

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.userDao();

        Log.i("CreateAccountTest", "created DB");
    }

    @After
    public void closeDb() {
        mDb.close();

        Log.i("CreateAccountTest", "closeDb: ");
    }

    // testing adding a user, switching activities and checking if user is in database

    @Test
    public void createAccountIntentTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = null;
        mTestUser.setUserId(5);
        assertEquals(5, mTestUser.getUserId());
        mDao.insertUser(mTestUser);
        int defaultValue = -1;

        assertNull(intent);


        intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.USER_ID_KEY,mDao.getUserByUsername("user999").getUserId());


        assertEquals(5, mTestUser.getUserId());

        assertEquals(mTestUser.getUserId(), intent.getIntExtra(Constants.USER_ID_KEY, defaultValue));
        assertTrue(mDao.userExists(mTestUser.getUsername()));
    }
}
