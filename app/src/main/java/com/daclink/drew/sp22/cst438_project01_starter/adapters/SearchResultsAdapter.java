package com.daclink.drew.sp22.cst438_project01_starter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.R;
import com.daclink.drew.sp22.cst438_project01_starter.models.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultHolder> {
    private List<Search> searchResults = new ArrayList<>();

    @NonNull
    @Override
    public SearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new SearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultHolder holder, int position) {
        Search results = searchResults.get(position);

        holder.titleTextView.setText(results.getTitle());
        holder.releasedDateTextView.setText(results.getYear());

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
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public void setResults(List<Search> results) {
        this.searchResults = results;
        notifyDataSetChanged();
    }

    class SearchResultHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView directorTextView;
        private TextView releasedDateTextView;
        private ImageView posterImageView;

        public SearchResultHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.movie_item_title);
            directorTextView = itemView.findViewById(R.id.movie_directors);
            releasedDateTextView = itemView.findViewById(R.id.movie_releaseDate);
            posterImageView = itemView.findViewById(R.id.movie_poster);
        }
    }
}
