package com.daclink.drew.sp22.cst438_project01_starter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.cst438_project01_starter.models.Volume;
import com.daclink.drew.sp22.cst438_project01_starter.R;
import com.daclink.drew.sp22.cst438_project01_starter.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResultsAdapter extends RecyclerView.Adapter<MovieSearchResultsAdapter.MovieSearchResultHolder> {
    private List<Volume> results = new ArrayList<>();

    @NonNull
    @Override
    public MovieSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieSearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSearchResultHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getReleaseDate());

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);
        }

        if (volume.getVolumeInfo().getDirectors() != null) {
            Util u = new Util();
            String authors = u.StringJoin(volume.getVolumeInfo().getDirectors(), ", ");
            holder.authorsTextView.setText(authors);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class MovieSearchResultHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;

        public MovieSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.movie_item_title);
            authorsTextView = itemView.findViewById(R.id.movie_item_directors);
            publishedDateTextView = itemView.findViewById(R.id.movie_item_releasedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.movie_item_smallThumbnail);
        }
    }
}
