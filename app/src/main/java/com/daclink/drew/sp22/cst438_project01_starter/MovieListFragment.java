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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.daclink.drew.sp22.cst438_project01_starter.adapters.MovieListAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentListBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Class: MovieListFragment.java
 * Description: Creates bindings and interactable
 * aspects of the movie list
 */
public class MovieListFragment extends Fragment {
    private static int mUserId;

    private List<String> mImdbIds;

    private static List<MovieEntity> mMovies = new ArrayList<>();
    private static MovieDao mMovieDao;

    private @NonNull
    FragmentListBinding mBinding;
    private static MovieListAdapter mAdapter;

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
        getDatabase();

        // refresh list results
        // initialize movie list adapter for recycler view
        mAdapter = new MovieListAdapter(getContext());
        refreshList();

        // set up the recycler view
        RecyclerView recyclerView = view.findViewById(R.id.fragment_list_movieListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    // get instance of database and return user DAO
    private void getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getContext().getApplicationContext());
        mMovieDao = db.movieDao();
    }

    // refresh the user list
    public static void refreshList() {
        mMovies.clear();
        mMovies = mMovieDao.getMoviesByUserId(mUserId);
        mAdapter.setResults(mMovies);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
