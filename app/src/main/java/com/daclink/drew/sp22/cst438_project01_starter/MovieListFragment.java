package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment {
    private int mUserId;

    private String mImdbId;
    private List<String> mImdbIds;

    private List<IndividualSearch> mMovies = new ArrayList<>();

    private @NonNull FragmentListBinding mBinding;
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

        mPrefs = getContext().getSharedPreferences(constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mUserId = mPrefs.getInt(constants.USER_ID_KEY, -1);

        mUserDao = getDatabase();
        mUser = mUserDao.getUserById(mUserId);
        mImdbIds = mUser.getImdbIds();

        // Toast.makeText(getContext().getApplicationContext(), " " + mImdbIds, Toast.LENGTH_SHORT).show();

        mAdapter = new MovieListAdapter(getContext());

        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        mViewModel.init();
        mViewModel.getResponseLiveData().observe(getViewLifecycleOwner(), new Observer<IndividualSearch>() {
            @Override
            public void onChanged(IndividualSearch movie) {
                if (movie.getResponse() != null) {
                    mMovies.add(movie);
                    mAdapter.setResults(mMovies);
                    // Toast.makeText(getContext().getApplicationContext(), " " + mMovies.get(0).getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        refreshList();

        RecyclerView recyclerView = view.findViewById(R.id.fragment_list_movieListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    private UserDao getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getContext().getApplicationContext());
        return db.userDao();
    }

    private void refreshList() {
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
