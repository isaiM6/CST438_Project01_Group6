package com.daclink.drew.sp22.cst438_project01_starter.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("Directors")
    @Expose
    private List<String> directors = null;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("movieLength")
    @Expose
    private int movieLength;

    @SerializedName("movieType")
    @Expose
    private String movieType;

    @SerializedName("imageLinks")
    @Expose
    private VolumeImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public String getMovieType() {
        return movieType;
    }

    public VolumeImageLinks getImageLinks() {
        return imageLinks;
    }
}
