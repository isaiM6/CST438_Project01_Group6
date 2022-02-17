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

    public MovieListAdapter(Context context) {
        this.mContext = context;
    }

    public MovieListAdapter() {

    }

    @Override
    public void onBindViewHolder(@NonNull MovieListHolder holder, int position) {
        IndividualSearch results = mResults.get(position);

        if (results.getTitle() != null) {
            holder.titleTextView.setText(results.getTitle());
        }

        if (results.getReleased() != null) {
            holder.releasedTextView.setText(results.getReleased());
        }

        if (results.getPoster() != null) {
            String imageUrl = results.getPoster()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.posterImageView);
        }

        if (results.getType() != null) {
            holder.directorTextView.setText(results.getType());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MovieDetailsActivity.newIntent(mContext.getApplicationContext(), results.getImdbID());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void setResults(List<IndividualSearch> results) {
        this.mResults = results;
        notifyDataSetChanged();
    }

    class MovieListHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView directorTextView;
        private TextView releasedTextView;
        private ImageView posterImageView;

        public MovieListHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.movie_item_title);
            directorTextView = itemView.findViewById(R.id.movie_directors);
            releasedTextView = itemView.findViewById(R.id.movie_releaseDate);
            posterImageView = itemView.findViewById(R.id.movie_poster);
        }
    }
}
