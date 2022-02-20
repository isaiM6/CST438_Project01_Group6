package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.lifecycle.MutableLiveData;
import com.daclink.drew.sp22.cst438_project01_starter.apis.SearchService;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.MovieRepository;
import org.junit.Test;
import static org.junit.Assert.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class: MovieRepositoryTest.java
 * Description: Unit tests for MovieRepository class
 */
public class MovieRepositoryTest {
    // tests creation of movie repository constructor
    @Test
    public void movieRepositoryConstructorTest() {
        MovieRepository movieRepository = new MovieRepository();
        assertNotNull(movieRepository.getResponseLiveData());

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        assertNotNull(interceptor);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        assertNotNull(client);
        assertTrue(client instanceof OkHttpClient);

        SearchService searchService = new Retrofit.Builder()
                .baseUrl(MovieRepository.SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchService.class);

        assertNotNull(searchService);
    }

    // tests the logic of the search movie by imdb ID method
    @Test
    public void searchMovieByIMDB_Id_Test() {
        MovieRepository movieRepository = new MovieRepository();
        String imdbId = "00000";

        movieRepository.searchMovieByIMDB_Id(imdbId);
        assertNull(movieRepository.getResponseLiveData().getValue());

        imdbId = "tt0145487";

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        SearchService searchService = new retrofit2.Retrofit.Builder()
                .baseUrl(MovieRepository.SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchService.class);
        MutableLiveData<MovieEntity> responseLiveData = new MutableLiveData<MovieEntity>();

        searchService.searchValuesByIMDB_Id(imdbId)
                .enqueue(new Callback<MovieEntity>() {
                    @Override
                    public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                        if (response.body() != null) {
                            responseLiveData.postValue(response.body());
                            System.out.println(response);
                            assertNotNull(responseLiveData.getValue());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieEntity> call, Throwable t) {
                        responseLiveData.postValue(null);
                        assertNull(responseLiveData.getValue());
                    }
                });
    }

    // tests getResponseLiveData method
    @Test
    public void getResponseLiveDataTest() {
        MovieRepository movieRepository = null;
        assertNull(movieRepository);

        movieRepository = new MovieRepository();
        assertNotNull(movieRepository.getResponseLiveData());
    }
}
