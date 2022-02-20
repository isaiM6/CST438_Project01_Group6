package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Class: MovieDetailsTest.java
 * Description: Instrumented tests for MovieDetailsActivity.java
 */
@RunWith(AndroidJUnit4.class)
public class MovieDetailsTest {
    private AppDatabase mDb;
    private UserDao mUserDao;
    private MovieDao mMovieDao;
    private UserEntity mTestUser = new UserEntity("testuser", "testuser", "Test User");
    private MovieEntity mTestMovie = new MovieEntity();

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mUserDao = mDb.userDao();
        mMovieDao = mDb.movieDao();

        Log.i("MovieDetailsTest", "created DB");
    }

    @After
    public void closeDb() {
        mDb.close();

        Log.i("MovieDetailsTest", "closeDb: ");
    }

    // tests logic of the setValues method
    @Test
    public void setValues() {
        String title = null;
        String year = null;
        String director = null;
        String actors = null;
        String metascore = null;
        String boxOffice = null;
        String released = null;
        String genre = null;
        String rated = null;
        String runtime = null;
        String plot = null;

        MovieEntity movie = new MovieEntity();
        assertNotNull(movie);

        movie.setTitle("Test Movie");
        movie.setYear("2000");
        movie.setDirector("Test Director");
        movie.setMetascore("10");
        movie.setBoxOffice("$100 million");
        movie.setReleased("01, January 2000");
        movie.setGenre("Test Genre");
        movie.setRated("R");
        movie.setRuntime("100 min");
        movie.setPlot("Test Plot");

        assertNotEquals(title, movie.getTitle());
        assertNotEquals(year, movie.getYear());
        assertNotEquals(director, movie.getDirector());
        assertNotEquals(metascore, movie.getMetascore());
        assertNotEquals(boxOffice, movie.getBoxOffice());
        assertNotEquals(released, movie.getReleased());
        assertNotEquals(genre, movie.getGenre());
        assertNotEquals(rated, movie.getRated());
        assertNotEquals(runtime, movie.getRuntime());
        assertNotEquals(plot, movie.getPlot());

        title = movie.getTitle();
        year = movie.getYear();
        director = movie.getDirector();
        metascore = movie.getMetascore();
        boxOffice = movie.getBoxOffice();
        released = movie.getReleased();
        genre = movie.getGenre();
        rated = movie.getRated();
        runtime = movie.getRuntime();
        plot = movie.getPlot();

        assertEquals(title, movie.getTitle());
        assertEquals(year, movie.getYear());
        assertEquals(director, movie.getDirector());
        assertEquals(metascore, movie.getMetascore());
        assertEquals(boxOffice, movie.getBoxOffice());
        assertEquals(released, movie.getReleased());
        assertEquals(genre, movie.getGenre());
        assertEquals(rated, movie.getRated());
        assertEquals(runtime, movie.getRuntime());
        assertEquals(plot, movie.getPlot());
    }

    // tests the logic of setImageView method
    @Test
    public void setImageViewTest() {
        MovieEntity movie = new MovieEntity();
        assertNotNull(movie);

        String imageUrl = movie.getPoster();

        assertNull(imageUrl);

        movie.setPoster("http://poster");
        imageUrl = movie.getPoster();

        assertNotNull(imageUrl);

        assertEquals("http://poster", imageUrl);

        imageUrl = imageUrl.replace("http://", "https://");

        assertNotEquals("http://poster", imageUrl);
        assertEquals("https://poster", imageUrl);
    }

    // tests adding a movie to a user's list
    @Test
    public void addMovieTest() {
        UserEntity user = null;
        assertNull(user);

        mUserDao.insertUser(mTestUser);
        user = mUserDao.getUserByUsername("testuser");

        assertNotNull(user);
        assertEquals(0, user.getImdbIds().size());

        assertTrue(user.addImdbId("100"));

        assertEquals(1, user.getImdbIds().size());
        mTestUser = user;
        mTestMovie.setImdbID("100");
        mTestMovie.setUserId(mTestUser.getUserId());

        mUserDao.updateUser(mTestUser);
        mMovieDao.insertMovie(mTestMovie);

        assertEquals(1, mUserDao.getUserByUsername("testuser").getImdbIds().size());
        assertEquals("100", mUserDao.getUserByUsername("testuser").getImdbIds().get(0));
        assertNotNull(mMovieDao.getMovieByUserId(1, "100"));
        assertEquals(1, mMovieDao.getMovieById(1).getUserId());
        assertEquals("100", mMovieDao.getMovieById(1).getImdbID());
    }

    // tests removal of movie from user's list
    @Test
    public void removeMovieTest() {
        UserEntity user;

        mUserDao.insertUser(mTestUser);
        user = mUserDao.getUserByUsername("testuser");
        mTestMovie.setUserId(1);

        assertTrue(user.addImdbId("100"));
        assertTrue(user.addImdbId("200"));
        assertTrue(user.addImdbId("300"));

        mTestMovie.setImdbID("100");
        mMovieDao.insertMovie(mTestMovie);
        mTestMovie.setImdbID("200");
        mMovieDao.insertMovie(mTestMovie);
        mTestMovie.setImdbID("300");
        mMovieDao.insertMovie(mTestMovie);

        assertEquals(3, user.getImdbIds().size());
        assertEquals(3, mMovieDao.getMoviesByUserId(1).size());

        user.getImdbIds().remove("200");
        mMovieDao.delete(mMovieDao.getMovieByUserId(1, "200"));
        assertNotEquals(3, user.getImdbIds().size());
        assertEquals(2, user.getImdbIds().size());
        assertNotEquals(3, mMovieDao.getMoviesByUserId(1).size());
        assertEquals(2, mMovieDao.getMoviesByUserId(1).size());

        mTestUser = user;
        mUserDao.updateUser(mTestUser);

        assertEquals(2, mUserDao.getUserByUsername("testuser").getImdbIds().size());
    }

    // tests creation of MovieDetailsActivity intent
    @Test
    public void movieDetailsIntentTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = null;

        assertNull(intent);

        intent = new Intent(context, MovieDetailsActivity.class);

        assertNotNull(intent);
    }
}
