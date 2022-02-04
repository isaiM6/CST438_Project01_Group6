package com.daclink.drew.sp22.cst438_project01_starter.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.daclink.drew.sp22.cst438_project01_starter.apis.MovieSearchService;
import com.daclink.drew.sp22.cst438_project01_starter.models.VolumesResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private static final String MOVIE_SEARCH_SERVICE_BASE_URL = "https://omdbapi.com/?apikey=4d25d61f";

    private MovieSearchService movieSearchService;
    private MutableLiveData<VolumesResponse> volumesResponseLiveData;

    public MovieRepository() {
        volumesResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        movieSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(MOVIE_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieSearchService.class);

    }

    public void searchVolumes(String title) {
        movieSearchService.searchVolumes(title)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}
