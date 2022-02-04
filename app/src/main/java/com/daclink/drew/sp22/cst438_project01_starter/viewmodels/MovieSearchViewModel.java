package com.daclink.drew.sp22.cst438_project01_starter.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.models.VolumesResponse;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.MovieRepository;

public class MovieSearchViewModel extends AndroidViewModel {
    private MovieRepository bookRepository;
    private LiveData<VolumesResponse> volumesResponseLiveData;

    public MovieSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bookRepository = new MovieRepository();
        volumesResponseLiveData = bookRepository.getVolumesResponseLiveData();
    }

    public void searchVolumes(String title) {
        bookRepository.searchVolumes(title);
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}
