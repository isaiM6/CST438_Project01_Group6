package com.daclink.drew.sp22.cst438_project01_starter.apis;

import com.daclink.drew.sp22.cst438_project01_starter.models.VolumesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieSearchService {
    @GET("/movies/v1/volumes")
    Call<VolumesResponse> searchVolumes(
            @Query("movieTitle") String title
    );
}
