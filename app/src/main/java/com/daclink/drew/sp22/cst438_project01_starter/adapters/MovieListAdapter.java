package com.daclink.drew.sp22.cst438_project01_starter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.cst438_project01_starter.MovieDetailsActivity;
import com.daclink.drew.sp22.cst438_project01_starter.R;
import com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch;

import java.util.ArrayList;
import java.util.List;

/*
 * Class: MovieListAdapter.java
 * Description: The movie list adapter class just creates a
 * set format and a RecyclerView for displaying movies.
 * */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListHolder> {
    private List<IndividualSearch> mResults = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public MovieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieListHolder(itemView);
    }

    // parametrized constructor that takes in application context
    public MovieListAdapter(Context context) {
        this.mContext = context;
    }

    // set up results to be shown in the UI
    @Override
    public void onBindViewHolder(@NonNull MovieListHolder holder, int position) {
        IndividualSearch results = mResults.get(position);

        holder.titleTextView.setText(results.getTitle());
        holder.directorTextView.setText(results.getYear());

        if (results.getPoster() != null) {
            String imageUrl = results.getPoster()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.posterImageView);
        }

        // shows expanded movie details if card is clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to movie details activity
                Intent intent = MovieDetailsActivity.newIntent(mContext.getApplicationContext(), results.getImdbID());
                mContext.startActivity(intent);
            }
        });
    }

    // returns number of results shown
    @Override
    public int getItemCount() {
        return mResults.size();
    }

    // set movie results to be displayed
    public void setResults(List<IndividualSearch> results) {
        this.mResults = results;
        notifyDataSetChanged();
    }

    // holds recycler view contents
    class MovieListHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView directorTextView;
        private ImageView posterImageView;

        public MovieListHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.movie_item_title);
            directorTextView = itemView.findViewById(R.id.movie_directors);
            posterImageView = itemView.findViewById(R.id.movie_poster);
        }
    }
}
