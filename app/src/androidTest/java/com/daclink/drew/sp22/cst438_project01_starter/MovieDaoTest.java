package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class: MovieDaoTest.java
 * Description: Instrumented tests for movie DAO
 */
public class MovieDaoTest {
    private AppDatabase mDb;
    private MovieDao mDao;
    private MovieEntity mTestMovie = new MovieEntity();

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.movieDao();

        Log.i("ChangePasswordTest", "created DB");
    }

    @After
    public void closeDb() {
        mDb.close();

        Log.i("ChangePasswordTest", "closeDb: ");
    }

    @Test
    public void insertMovieTest() {

    }

    @Test
    public void updateMovieTest() {

    }

    @Test
    public void deleteMovieTest() {

    }

    @Test
    public void getMovieByIdTest() {

    }

    @Test
    public void getMovieByUserId() {

    }

    @Test
    public void getMoviesByUserId() {

    }

    @Test
    public void getMovieByImdbId() {

    }

    @Test
    public void getAllMovies() {

    }
}
