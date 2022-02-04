package com.daclink.drew.sp22.cst438_project01_starter.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolumeImageLinks {
    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
