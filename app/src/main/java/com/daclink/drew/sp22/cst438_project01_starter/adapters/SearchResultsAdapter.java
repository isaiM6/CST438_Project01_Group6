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

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultHolder> {
    private APIValues results = new APIValues();

    @NonNull
    @Override
    public SearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new SearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultHolder holder, int position) {
        APIValues volume = results;

        holder.titleTextView.setText(volume.getTitle());
        holder.releasedDateTextView.setText(volume.getReleased());

        if (volume.getPoster() != null) {
            String imageUrl = volume.getPoster()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.posterImageView);
        }

        if (volume.getDirector() != null) {
            holder.directorTextView.setText(volume.getDirector());
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setResults(APIValues results) {
        this.results = results;
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
