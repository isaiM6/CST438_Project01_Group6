package com.daclink.drew.sp22.cst438_project01_starter.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.MovieRepository;

/*
 * Class: DetailsViewModel.java
 * Description: The DetailsViewModel in the same
 * way as 'SearchViewModel.java' creates a landing
 * place for API responses but for more detailed
 * information
 * Note: see SearchViewModel.java
 * */

public class DetailsViewModel extends AndroidViewModel {
    private MovieRepository mRepo;
    private LiveData<IndividualSearch> mResponseLiveData;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }

    // initialize view model's repository and response live data
    public void init() {
        mRepo = new MovieRepository();
        mResponseLiveData = mRepo.getResponseLiveData();
    }

    // search for movie by imdbId
    public void searchMovie(String imdbId) {
        mRepo.searchMovieByIMDB_Id(imdbId);
    }

    // get response from API
    public LiveData<IndividualSearch> getResponseLiveData() {
        return mResponseLiveData;
    }
}
