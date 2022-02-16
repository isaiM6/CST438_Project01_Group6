package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.daclink.drew.sp22.cst438_project01_starter.adapters.MovieListAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.adapters.SearchResultsAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.DetailsViewModel;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

public class MovieListActivity extends AppCompatActivity {
    private DetailsViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private MovieListAdapter mAdapter;

    private List<MovieEntity> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);

        mRecyclerView = findViewById(R.id.movielist_movieListRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MovieListActivity.class);
        return intent;
    }
}