package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.daclink.drew.sp22.cst438_project01_starter.adapters.MovieListAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Class: MovieListAdapterTest.java
 * Description: Instrumented tests for the movie list adapter
 */
@RunWith(AndroidJUnit4.class)
public class MovieListAdapterTest {
    private MovieEntity mMovie = new MovieEntity();
    private List<MovieEntity> mMovies = new ArrayList<>();
    private MovieListAdapter mAdapter;

    // create the movie list adapter
    @Before
    public void createAdapter() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mAdapter = new MovieListAdapter(context);

        Log.i("MovieListAdapterTest", "created adapter");
    }

    // tests creation movie list adapter constructor
    @Test
    public void movieListAdapterConstructorTest() {
        mAdapter = null;
        assertNull(mAdapter);

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mAdapter = new MovieListAdapter(context);

        assertNotNull(mAdapter);
    }

    // tests logic of onBindViewHolder method
    @Test
    public void onBindViewHolderTest() {
        String title = null;
        String year = null;
        String poster = null;

        assertNull(title);
        assertNull(year);
        assertNull(poster);

        title = "Test Title";
        year = "Test Year";
        poster = "http://Test Poster";

        mMovie.setTitle(title);
        mMovie.setYear(year);
        mMovie.setPoster(poster);

        assertNotNull(title);
        assertNotNull(year);
        assertNotNull(poster);

        String imageUrl = mMovie.getPoster().replace("http://", "https://");

        assertEquals("https://Test Poster", imageUrl);
    }

    // tests getItemCount and setResults methods
    @Test
    public void getItemCountAndSetResultsTest() {
        assertEquals(0, mAdapter.getItemCount());

        mMovies.add(mMovie);
        mMovies.add(mMovie);
        mMovies.add(mMovie);
        mAdapter.setResults(mMovies);

        assertNotEquals(0, mAdapter.getItemCount());
        assertEquals(3, mAdapter.getItemCount());
    }
}
