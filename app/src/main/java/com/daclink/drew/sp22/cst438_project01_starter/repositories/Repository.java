package com.daclink.drew.sp22.cst438_project01_starter.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.daclink.drew.sp22.cst438_project01_starter.apis.SearchService;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String SEARCH_SERVICE_BASE_URL = "https://omdbapi.com/";

    private SearchService searchService;
    private MutableLiveData<APIValues> responseLiveData;

    public Repository() {
        responseLiveData = new MutableLiveData<APIValues>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        searchService = new retrofit2.Retrofit.Builder()
                .baseUrl(SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchService.class);

    }

    public void searchVolumes(String title) {
        searchService.searchValues(title)
                .enqueue(new Callback<APIValues>() {
                    @Override
                    public void onResponse(Call<APIValues> call, Response<APIValues> response) {
                        if (response.body() != null) {
                            responseLiveData.postValue(response.body());
                            System.out.println(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<APIValues> call, Throwable t) {
                        responseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<APIValues> getResponseLiveData() {
        return responseLiveData;
    }
}
