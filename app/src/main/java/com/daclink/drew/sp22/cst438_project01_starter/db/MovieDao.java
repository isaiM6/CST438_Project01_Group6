package com.daclink.drew.sp22.cst438_project01_starter.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insertMovie(MovieEntity... movies);

    @Update
    void updateMovie(MovieEntity... movies);

    @Delete
    void delete(MovieEntity... movies);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " WHERE movieId = :movieId")
    MovieEntity getMovieById(int movieId);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " WHERE userId = :userId")
    MovieEntity getMovieByUserId(int userId);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " WHERE title = :title")
    MovieEntity getMovieByTitle(String title);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " WHERE imdbID = :imdbId")
    MovieEntity getMovieByImdbId(String imdbId);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " ORDER BY title DESC")
    List<MovieEntity> getAllMovies();
}
