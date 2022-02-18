package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMovieDetailsBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.models.MovieEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.DetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/*
 * Class: MoieDetailsActivity.java
 * Description: Creates bindings and interactable
 * aspects of the detailed movie display
 * */

public class MovieDetailsActivity extends AppCompatActivity {
    private int mUserId;

    private String mTitle;
    private String mYear;
    private String mDirector;
    private String mActors;
    private String mMetascore;
    private String mBoxOffice;
    private String mReleased;
    private String mGenre;
    private String mRated;
    private String mRuntime;
    private String mPlot;
    private String mImageUrl;
    private String mImdbId;

    private MovieEntity mMovie;

    private TextView mTitleTextView;
    private TextView mDirectorTextView;
    private TextView mActorsTextView;
    private TextView mMetascoreTextView;
    private TextView mBoxOfficeTextView;
    private TextView mReleasedTextView;
    private TextView mGenreTextView;
    private TextView mRatedTextView;
    private TextView mPlotTextView;

    private ImageView mPosterImageView;

    private ActivityMovieDetailsBinding mBinding;

    private FloatingActionButton mFab;

    private DetailsViewModel mViewModel;

    private UserEntity mUser;
    private UserDao mUserDao;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // get user's shared preferences
        mPrefs = getSharedPreferences(constants.SHARED_PREF_NAME, MODE_PRIVATE);
        mUserId = mPrefs.getInt(constants.USER_ID_KEY, -1);

        // initialize floating action button
        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFab = mBinding.movieDetailsFab;

        // initialize user and user DAO
        mUserDao = getDatabase();
        mUser = mUserDao.getUserById(mUserId);

        // imdbId used to retrieve movie info
        mImdbId = getIntent().getStringExtra(constants.IMDB_ID);

        // change floating action button icon
        changeIcon();

        // set up view model
        mViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        mViewModel.init();

        // view model observes API consumption
        mViewModel.getResponseLiveData().observe(this, new Observer<MovieEntity>() {
            @Override
            public void onChanged(MovieEntity movie) {
                if (movie.getResponse() != null) {
                    mMovie = movie;

                    // set up the UI
                    wireUpDisplay();
                    setValues();
                    setImageView();
                    setTextViews();
                }
            }
        });

        // search for the movie
        mViewModel.searchMovie(mImdbId);

        // floating actioin button on click listener
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mUser.addImdbId(mImdbId)) {
                    // remove movie from user's list

                    mUser.getImdbIds().remove(mImdbId);
                    mUserDao.updateUser(mUser);

                    // change floating action button icon
                    changeIcon();

                    Snackbar.make(v, "Movie Successfully Removed", Snackbar.LENGTH_LONG).show();
                } else {
                    // add movie to user's list

                    mUserDao.updateUser(mUser);

                    // change floating action button icon
                    changeIcon();

                    Snackbar.make(v, "Movie Successfully Saved", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    // get instance of database and return user DAO
    private UserDao getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        return db.userDao();
    }

    // change floating action button icon
    private void changeIcon() {
        // changes icon to remove if user already has movie saved, changes icon to add if they don't
        if (mUser.getImdbIds().contains(mImdbId)) {
            mFab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_remove));
        } else {
            mFab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add));
        }
    }

    // wire up text views
    private void wireUpDisplay() {
        mTitleTextView = findViewById(R.id.movie_details_title);
        mDirectorTextView = findViewById(R.id.movie_details_director);
        mActorsTextView = findViewById(R.id.movie_details_actors);
        mMetascoreTextView = findViewById(R.id.movie_details_metascore);
        mBoxOfficeTextView = findViewById(R.id.movie_details_box_office);
        mReleasedTextView = findViewById(R.id.movie_details_released);
        mGenreTextView = findViewById(R.id.movie_details_genre);
        mRatedTextView = findViewById(R.id.movie_details_rated);
        mPlotTextView = findViewById(R.id.movie_details_plot);
        mPosterImageView = findViewById(R.id.movie_details_imageview);
    }

    // initialize local variables
    private void setValues() {
        mTitle = mMovie.getTitle();
        mYear = mMovie.getYear();
        mDirector = mMovie.getDirector();
        mActors = mMovie.getActors();
        mMetascore = mMovie.getMetascore();
        mBoxOffice = mMovie.getBoxOffice();
        mReleased = mMovie.getReleased();
        mGenre = mMovie.getGenre();
        mRated = mMovie.getRated();
        mRuntime = mMovie.getRuntime();
        mPlot = mMovie.getPlot();
    }

    // set up the poster using Glide
    private void setImageView() {
        mImageUrl = mMovie.getPoster();

        if (mImageUrl != null) {
            mImageUrl = mImageUrl.replace("http://", "https://");

            Glide.with(getApplicationContext())
                    .load(mImageUrl)
                    .into(mPosterImageView);
        }
    }

    // set text view information with movie info from API
    private void setTextViews() {
        mTitleTextView.setText(mTitle + ", " + mYear);
        mDirectorTextView.append(": " + mDirector);
        mActorsTextView.setText("Starring: " + mActors);
        mMetascoreTextView.append(": " + mMetascore + "/100");
        mBoxOfficeTextView.append(": " + mBoxOffice);
        mReleasedTextView.append(": " + mReleased);
        mGenreTextView.append(": " + mGenre);
        mRatedTextView.setText(mRated + ", " + mRuntime);
        mPlotTextView.append(":\n\n     " + mPlot);
    }

    // menu setup
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.exit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // factory intent to switch activities
    public static Intent newIntent(Context packageContext, String imdbId) {
        Intent intent = new Intent(packageContext, MovieDetailsActivity.class);
        intent.putExtra(constants.IMDB_ID, imdbId);
        return intent;
    }
}