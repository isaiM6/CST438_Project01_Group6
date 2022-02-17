package com.daclink.drew.sp22.cst438_project01_starter.apis;

import static com.daclink.drew.sp22.cst438_project01_starter.utilities.constants.API_KEY;

import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Class: SearchService.java
 * Description: The SearchService interface just provides
 * retrofit the information in order to process API requests.
 * Note: The API key is static.
 * */

public interface SearchService {
    @GET("?apikey=" + API_KEY + "&type=movie")
    Call<APIValues> searchValues(
            @Query("s") String title
    );

    @GET("?apikey=4d25d61f" + API_KEY + "")
    Call<IndividualSearch> searchValuesByIMDB_Id(
            @Query("i") String imdbId
    );
}
