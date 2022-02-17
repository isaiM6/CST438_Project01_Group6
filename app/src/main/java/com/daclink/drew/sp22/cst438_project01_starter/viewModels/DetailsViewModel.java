package com.daclink.drew.sp22.cst438_project01_starter.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.R;
import com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.MovieRepository;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.Repository;

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

    public void init() {
        mRepo = new MovieRepository();
        mResponseLiveData = mRepo.getResponseLiveData();
    }

    public void searchMovie(String imdbId) {
        mRepo.searchMovieByIMDB_Id(imdbId);
    }

    public LiveData<IndividualSearch> getResponseLiveData() {
        return mResponseLiveData;
    }
}
