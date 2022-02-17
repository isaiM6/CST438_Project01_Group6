package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.cst438_project01_starter.adapters.MovieListAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMovieDetailsBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.DetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

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

    private IndividualSearch mMovie;

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

        mPrefs = getSharedPreferences(constants.SHARED_PREF_NAME, MODE_PRIVATE);
        mUserId = mPrefs.getInt(constants.USER_ID_KEY, -1);

        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFab = mBinding.movieDetailsFab;

        mUserDao = getDatabase();
        mUser = mUserDao.getUserById(mUserId);
        mImdbId = getIntent().getStringExtra(constants.IMDB_ID);

        changeIcon();

        mViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        mViewModel.init();
        mViewModel.getResponseLiveData().observe(this, new Observer<IndividualSearch>() {
            @Override
            public void onChanged(IndividualSearch movie) {
                if (movie.getResponse() != null) {
                    mMovie = movie;
                    wireUpDisplay();
                    setValues();
                    setImageView();
                    setTextViews();
                }
            }
        });

        mViewModel.searchMovie(mImdbId);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mUser.addImdbId(mImdbId)) {
                    mUser.getImdbIds().remove(mImdbId);
                    mUserDao.updateUser(mUser);

                    changeIcon();

                    Snackbar.make(v, "Movie Successfully Removed", Snackbar.LENGTH_LONG).show();
                } else {
                    mUserDao.updateUser(mUser);

                    changeIcon();

                    Snackbar.make(v, "Movie Successfully Saved", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private UserDao getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        return db.userDao();
    }

    private void changeIcon() {
        if (mUser.getImdbIds().contains(mImdbId)) {
            mFab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_remove));
        } else {
            mFab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add));
        }
    }

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
        mYear = mMovie.getYear();
    }

    private void setImageView() {
        mImageUrl = mMovie.getPoster();

        if (mImageUrl != null) {
            mImageUrl.replace("http://", "https://");

            Glide.with(getApplicationContext())
                    .load(mImageUrl)
                    .into(mPosterImageView);
        }
    }

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

    public static Intent newIntent(Context packageContext, String imdbId) {
        Intent intent = new Intent(packageContext, MovieDetailsActivity.class);
        intent.putExtra(constants.IMDB_ID, imdbId);
        return intent;
    }
}