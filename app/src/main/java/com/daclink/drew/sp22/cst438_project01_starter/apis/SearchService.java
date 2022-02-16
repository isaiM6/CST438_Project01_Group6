package com.daclink.drew.sp22.cst438_project01_starter.apis;

import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("?apikey=4d25d61f&type=movie")
    Call<APIValues> searchValues(
            @Query("s") String title
    );

    @GET("?apikey=4d25d61f&type=movie")
    Call<APIValues> searchValuesByIMDB_Id(
            @Query("i") String imdbId
    );
}
