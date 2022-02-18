package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.drew.sp22.cst438_project01_starter.adapters.MovieListAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentListBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Class: MovieListFragment.java
 * Description: Creates bindings and interactable
 * aspects of the movie list
 * */

public class MovieListFragment extends Fragment {
    private int mUserId;

    private List<String> mImdbIds;

    private List<MovieEntity> mMovies = new ArrayList<>();

    private @NonNull
    FragmentListBinding mBinding;
    private DetailsViewModel mViewModel;
    private MovieListAdapter mAdapter;

    private UserEntity mUser;
    private UserDao mUserDao;

    private SharedPreferences mPrefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentListBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get user's shared preferences
        mPrefs = getContext().getSharedPreferences(constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mUserId = mPrefs.getInt(constants.USER_ID_KEY, -1);

        // initialize user and user DAO
        mUserDao = getDatabase();
        mUser = mUserDao.getUserById(mUserId);

        // initialize movie list adapter for recycler view
        mAdapter = new MovieListAdapter(getContext());

        // set up view model
        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        mViewModel.init();

        // view model observes API consumption
        mViewModel.getResponseLiveData().observe(getViewLifecycleOwner(), new Observer<MovieEntity>() {
            @Override
            public void onChanged(MovieEntity movie) {
                if (movie.getResponse() != null) {
                    // passes the user's movies to the movie list adapter
                    mMovies.add(movie);
                    mAdapter.setResults(mMovies);
                }
            }
        });

        // refresh the user list
        refreshList();

        // set up the recycler view
        RecyclerView recyclerView = view.findViewById(R.id.fragment_list_movieListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    // get instance of database and return user DAO
    private UserDao getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getContext().getApplicationContext());
        return db.userDao();
    }

    // refresh the user list
    public void refreshList() {
        mImdbIds = mUser.getImdbIds();
        mMovies.clear();

        // searching for each movie by imdbId one at a time
        for (String imdbId : mImdbIds) {
            mViewModel.searchMovie(imdbId);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
