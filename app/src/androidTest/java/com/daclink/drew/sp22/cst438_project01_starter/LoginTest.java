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
 * Class: LoginTest.java
 * Description: Instrumented test for LoginActivity.java
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {
    private AppDatabase mDb;
    private UserDao mDao;
    private UserEntity mTestUser = new UserEntity("testuser", "testuser", "Test User");

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.userDao();

        Log.i("LoginTest", "created DB");
    }

    @After
    public void closeDb() {
        mDb.close();

        Log.i("LoginTest", "closeDb: ");
    }

    //

    @Test
    public void loginIntentTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = null;
        int defaultValue = -1;

        assertNull(intent);
        intent = new Intent(context, MainActivity.class);
        assertEquals(defaultValue, intent.getIntExtra(Constants.USER_ID_KEY, defaultValue));
        mTestUser.setUserId(1);
        intent.putExtra(Constants.USER_ID_KEY, mTestUser.getUserId());

        assertEquals(1, mTestUser.getUserId());

        assertNotEquals(defaultValue, intent.getIntExtra(Constants.USER_ID_KEY, defaultValue));;
        assertEquals(mTestUser.getUserId(), intent.getIntExtra(Constants.USER_ID_KEY, defaultValue));
    }
}
