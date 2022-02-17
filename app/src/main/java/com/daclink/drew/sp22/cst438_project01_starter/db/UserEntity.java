package com.daclink.drew.sp22.cst438_project01_starter.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = AppDatabase.USER_TABLE)
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @NonNull
    private String username;
    private String password;
    private String name;
    private ArrayList<String> imdbIds = new ArrayList<>();
    private ArrayList<String> favMovies = new ArrayList<>();
    private ArrayList<String> searchHistory = new ArrayList<>();


    public UserEntity(@NonNull String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImdbIds() {
        return imdbIds;
    }

    public void setImdbIds(ArrayList<String> imdbIds) {
        this.imdbIds = imdbIds;
    }

    public ArrayList<String> getFavMovies() {
        return favMovies;
    }

    public void setFavMovies(ArrayList<String> favMovies) {
        this.favMovies = favMovies;
    }

    public boolean addImdbId(String imdbId) {
        if (!imdbIds.contains(imdbId)) {
            return imdbIds.add(imdbId);
        }
        return false;
    }

    public boolean addFavMovie(String movie){
        if(!favMovies.contains(movie)){
            return favMovies.add(movie);
        }
        return false;
    }

    public boolean addSearchHistory(String movie){
        if(!searchHistory.contains(movie)){
            return searchHistory.add(movie);
        }
        return false;
    }

    public ArrayList<String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(ArrayList<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", favMovies=" + favMovies +
                ", searchHistory=" + searchHistory +
                '}';
    }
}
