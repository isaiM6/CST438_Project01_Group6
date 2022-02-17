package com.daclink.drew.sp22.cst438_project01_starter.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.cst438_project01_starter.MainActivity;
import com.daclink.drew.sp22.cst438_project01_starter.MovieDetailsActivity;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.R;
import com.daclink.drew.sp22.cst438_project01_starter.models.Search;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;

import java.util.ArrayList;
import java.util.List;

/*
 * Class: SearchResultsAdapter.java
 * Description: The SearchResultsAdapter creates a format
 * to display to the user the resulting movies from their search result.
 * */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultHolder> {
    private List<Search> searchResults = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public SearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new SearchResultHolder(itemView);
    }

    public SearchResultsAdapter(Context context) {
        this.context = context;
    }

    public SearchResultsAdapter() {

    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultHolder holder, int position) {
        Search results = searchResults.get(position);

        if (results.getTitle() != null) {
            holder.titleTextView.setText(results.getTitle());
        }
        if (results.getYear() != null) {
            holder.releasedDateTextView.setText(results.getYear());
        }

        if (results.getPoster() != null) {
            String imageUrl = results.getPoster()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.posterImageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MovieDetailsActivity.newIntent(context.getApplicationContext(), results.getImdbId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (searchResults == null) {
            return 0;
        }
        return searchResults.size();
    }

    public void setResults(List<Search> results) {
        this.searchResults = results;
        notifyDataSetChanged();
    }

    class SearchResultHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView releasedDateTextView;
        private ImageView posterImageView;

        public SearchResultHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.movie_item_title);
            releasedDateTextView = itemView.findViewById(R.id.movie_releaseDate);
            posterImageView = itemView.findViewById(R.id.movie_poster);
        }
    }
}
