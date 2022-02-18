package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;
import android.util.Log;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

/**
 * Class: MovieListTest.java
 * Description: Instrumented tests for movie list fragment and functionality
 */
@RunWith(AndroidJUnit4.class)
public class MovieListTest {
    private AppDatabase mDb;
    private UserDao mDao;
    private UserEntity mTestUser = new UserEntity("testuser", "testuser", "Test User");

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.userDao();

        Log.i("MovieListTest", "created DB");
    }

    @After
    public void closeDb() {
        mDb.close();

        Log.i("MovieListTest", "closeDb: ");
    }
}
