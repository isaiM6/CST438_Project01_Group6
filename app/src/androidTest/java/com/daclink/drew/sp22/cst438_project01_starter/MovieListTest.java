package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.daclink.drew.sp22.cst438_project01_starter.adapters.MovieListAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class: MovieListTest.java
 * Description: Instrumented tests for movie list fragment and functionality
 */
@RunWith(AndroidJUnit4.class)
public class MovieListTest {
    private AppDatabase mDb;
    private MovieDao mMovieDao;
    private MovieEntity mMovie = new MovieEntity();
    private List<MovieEntity> mMovies = new ArrayList<>();
    private MovieListAdapter mAdapter;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mMovieDao = mDb.movieDao();
        mAdapter = new MovieListAdapter(context);

        Log.i("MovieListTest", "created DB");
    }

    @After
    public void closeDb() {
        mDb.close();

        Log.i("MovieListTest", "closeDb: ");
    }

    // tests the refresh list method in the movie list class
    @Test
    public void refreshListTest() {
        assertNotNull(mMovies);
        assertNotNull(mMovieDao);
        assertNotNull(mAdapter);

        mMovies.clear();
        assertEquals(0, mMovies.size());

        mMovie.setUserId(1);
        mMovieDao.insertMovie(mMovie, mMovie, mMovie);
        assertEquals(3, mMovieDao.getAllMovies().size());

        mMovies = mMovieDao.getMoviesByUserId(1);
        assertNotEquals(0, mMovies.size());
        assertEquals(3, mMovies.size());

        mAdapter.setResults(mMovies);
        assertEquals(3, mAdapter.getItemCount());
    }
}
