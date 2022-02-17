/**
 * Author: Pedro Gutierrez Jr.
 * Last Modified: 02/17/2022
 * Abstract: View model for individual movies
 */
package com.daclink.drew.sp22.cst438_project01_starter.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.MovieRepository;

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
