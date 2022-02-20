package com.daclink.drew.sp22.cst438_project01_starter;

import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class: MovieEntityTest.java
 * Description: Unit tests for MovieEntity class
 */
public class MovieEntityTest {
    private MovieEntity mMovie = new MovieEntity();
    private int mUserId = 1;
    private String mTitle = "Test Title";
    private String mYear = "Test Year";
    private String mRated = "Test Rated";
    private String mReleased = "Test Released";
    private String mRuntime = "Test Runtime";
    private String mGenre = "Test Genre";
    private String mDirector = "Test Director";
    private String mWriter = "Test Writer";
    private String mActors = "Test Actors";
    private String mPlot = "Test Plot";
    private String mLanguage = "Test Language";
    private String mCountry = "Test Country";
    private String mAwards = "Test Awards";
    private String mPoster = "Test Poster";
    private String mMetascore = "Test Metascore";
    private String mImdbRating = "Test ImdbRating";
    private String mImdbVotes = "Test ImdbVotes";
    private String mImdbId = "Test ImdbId";
    private String mType = "Test Type";
    private String mDvd = "Test Dvd";
    private String mBoxOffice = "Test Box Office";
    private String mProduction = "Test Production";
    private String mWebsite = "Test Website";
    private String mResponse = "Test Response";

    @Test
    public void movieConstructorTest() {
        assertNotNull(mMovie);

        mMovie = null;
        assertNull(mMovie);

        mMovie = new MovieEntity(mUserId,
                                mTitle,
                                mYear,
                                mRated,
                                mReleased,
                                mRuntime,
                                mGenre,
                                mDirector,
                                mWriter,
                                mActors,
                                mPlot,
                                mLanguage,
                                mCountry,
                                mAwards,
                                mPoster,
                                mMetascore,
                                mImdbRating,
                                mImdbVotes,
                                mImdbId,
                                mType,
                                mDvd,
                                mBoxOffice,
                                mProduction,
                                mWebsite,
                                mResponse);

        assertNotNull(mMovie);
    }

    @Test
    public void movieGettersTest() {
        mMovie = new MovieEntity(mUserId,
                mTitle,
                mYear,
                mRated,
                mReleased,
                mRuntime,
                mGenre,
                mDirector,
                mWriter,
                mActors,
                mPlot,
                mLanguage,
                mCountry,
                mAwards,
                mPoster,
                mMetascore,
                mImdbRating,
                mImdbVotes,
                mImdbId,
                mType,
                mDvd,
                mBoxOffice,
                mProduction,
                mWebsite,
                mResponse);

        assertNotNull(mMovie);

        assertEquals(1, mMovie.getUserId());
        assertEquals("Test Title", mMovie.getTitle());
        assertEquals("Test Year", mMovie.getYear());
        assertEquals("Test Rated", mMovie.getRated());
        assertEquals("Test Runtime", mMovie.getRuntime());
        assertEquals("Test Genre", mMovie.getGenre());
        assertEquals("Test Director", mMovie.getDirector());
        assertEquals("Test Writer", mMovie.getWriter());
        assertEquals("Test Actors", mMovie.getActors());
        assertEquals("Test Plot", mMovie.getPlot());
        assertEquals("Test Language", mMovie.getLanguage());
        assertEquals("Test Country", mMovie.getCountry());
        assertEquals("Test Awards", mMovie.getAwards());
        assertEquals("Test Poster", mMovie.getPoster());
        assertEquals("Test Metascore", mMovie.getMetascore());
        assertEquals("Test ImdbRating", mMovie.getImdbRating());
        assertEquals("Test ImdbVotes", mMovie.getImdbVotes());
        assertEquals("Test ImdbId", mMovie.getImdbID());
        assertEquals("Test Type", mMovie.getType());
        assertEquals("Test Dvd", mMovie.getDvd());
        assertEquals("Test Box Office", mMovie.getBoxOffice());
        assertEquals("Test Production", mMovie.getProduction());
        assertEquals("Test Website", mMovie.getWebsite());
        assertEquals("Test Response", mMovie.getResponse());
    }

    @Test
    public void movieSettersTest() {
        mMovie.setUserId(mUserId);
        mMovie.setTitle(mTitle);
        mMovie.setYear(mYear);
        mMovie.setRated(mRated);
        mMovie.setRuntime(mRuntime);
        mMovie.setGenre(mGenre);
        mMovie.setDirector(mDirector);
        mMovie.setWriter(mWriter);
        mMovie.setActors(mActors);
        mMovie.setPlot(mPlot);
        mMovie.setLanguage(mLanguage);
        mMovie.setCountry(mCountry);
        mMovie.setAwards(mAwards);
        mMovie.setPoster(mPoster);
        mMovie.setMetascore(mMetascore);
        mMovie.setImdbRating(mImdbRating);
        mMovie.setImdbVotes(mImdbVotes);
        mMovie.setImdbID(mImdbId);
        mMovie.setType(mType);
        mMovie.setDvd(mDvd);
        mMovie.setBoxOffice(mBoxOffice);
        mMovie.setProduction(mProduction);
        mMovie.setWebsite(mWebsite);
        mMovie.setResponse(mResponse);

        assertEquals(1, mMovie.getUserId());
        assertEquals("Test Title", mMovie.getTitle());
        assertEquals("Test Year", mMovie.getYear());
        assertEquals("Test Rated", mMovie.getRated());
        assertEquals("Test Runtime", mMovie.getRuntime());
        assertEquals("Test Genre", mMovie.getGenre());
        assertEquals("Test Director", mMovie.getDirector());
        assertEquals("Test Writer", mMovie.getWriter());
        assertEquals("Test Actors", mMovie.getActors());
        assertEquals("Test Plot", mMovie.getPlot());
        assertEquals("Test Language", mMovie.getLanguage());
        assertEquals("Test Country", mMovie.getCountry());
        assertEquals("Test Awards", mMovie.getAwards());
        assertEquals("Test Poster", mMovie.getPoster());
        assertEquals("Test Metascore", mMovie.getMetascore());
        assertEquals("Test ImdbRating", mMovie.getImdbRating());
        assertEquals("Test ImdbVotes", mMovie.getImdbVotes());
        assertEquals("Test ImdbId", mMovie.getImdbID());
        assertEquals("Test Type", mMovie.getType());
        assertEquals("Test Dvd", mMovie.getDvd());
        assertEquals("Test Box Office", mMovie.getBoxOffice());
        assertEquals("Test Production", mMovie.getProduction());
        assertEquals("Test Website", mMovie.getWebsite());
        assertEquals("Test Response", mMovie.getResponse());
    }
}
