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
    private MovieEntity mMovie = new MovieEntity();

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

    // tests insert movie room method
    @Test
    public void insertMovieTest() {
        assertEquals(0, mDao.getAllMovies().size());

        mDao.insertMovie(mMovie);
        assertNotEquals(0, mDao.getAllMovies().size());
        assertEquals(1, mDao.getAllMovies().size());
        assertNotNull(mDao.getMovieById(1));
    }

    // tests update movie room method
    @Test
    public void updateMovieTest() {
        mDao.insertMovie(mMovie);
        assertNotNull(mDao.getMovieById(1));

        mMovie = mDao.getMovieById(1);
        mMovie.setImdbID("ImdbId");
        mDao.updateMovie(mMovie);

        assertNotNull(mDao.getMovieByImdbId("ImdbId"));
        assertEquals(1, mDao.getMovieByImdbId("ImdbId").getMovieId());
    }

    // tests delete movie room method
    @Test
    public void deleteMovieTest() {
        mDao.insertMovie(mMovie);
        assertEquals(1, mDao.getAllMovies().size());

        mMovie = mDao.getMovieById(1);
        mDao.delete(mMovie);

        assertNotEquals(1, mDao.getAllMovies().size());
        assertEquals(0, mDao.getAllMovies().size());
        assertNull(mDao.getMovieById(1));
    }

    // tests ability to get movie from database by its unique id
    @Test
    public void getMovieByIdTest() {
        assertNull(mDao.getMovieById(1));

        mDao.insertMovie(mMovie);
        assertNotNull(mDao.getMovieById(1));

        assertNull(mDao.getMovieById(2));
        mDao.insertMovie(mMovie);
        assertNotNull(mDao.getMovieById(2));
    }

    // tests ability to get movie from database by a user's id
    @Test
    public void getMovieByUserId() {
        assertNull(mDao.getMovieByUserId(1, "ImdbId"));

        mMovie.setUserId(1);
        mMovie.setImdbID("ImdbId");
        mDao.insertMovie(mMovie);
        assertNotNull(mDao.getMovieByUserId(1, "ImdbId"));
    }

    // tests ability to get all movies from database matching a user's id
    @Test
    public void getMoviesByUserId() {
        assertEquals(0, mDao.getMoviesByUserId(1).size());

        mMovie.setUserId(1);
        mDao.insertMovie(mMovie);
        mDao.insertMovie(mMovie);

        assertEquals(2, mDao.getAllMovies().size());
        assertEquals(2, mDao.getMoviesByUserId(1).size());

        mDao.delete(mDao.getMovieById(2));

        assertNotEquals(2, mDao.getAllMovies().size());
        assertNotEquals(2, mDao.getMoviesByUserId(1).size());
        assertEquals(1, mDao.getAllMovies().size());
        assertEquals(1, mDao.getMoviesByUserId(1).size());
    }

    // tests ability to get movie from database by its Imdb id
    @Test
    public void getMovieByImdbId() {
        mDao.insertMovie(mMovie);

        assertNotNull(mDao.getMovieById(1));
        assertNull(mDao.getMovieByImdbId("ImdbId"));

        mMovie = mDao.getMovieById(1);
        mMovie.setImdbID("ImdbId");
        mDao.updateMovie(mMovie);

        assertNotNull(mDao.getMovieByImdbId("ImdbId"));
        assertEquals("ImdbId", mDao.getMovieByImdbId("ImdbId").getImdbID());
    }

    // tests ability to get all existing movies in the database
    @Test
    public void getAllMovies() {
        assertEquals(0, mDao.getAllMovies().size());

        mDao.insertMovie(mMovie, mMovie, mMovie);

        assertNotEquals(0, mDao.getAllMovies().size());
        assertEquals(3, mDao.getAllMovies().size());

        mDao.delete(mDao.getMovieById(3));

        assertEquals(2, mDao.getAllMovies().size());
    }
}
