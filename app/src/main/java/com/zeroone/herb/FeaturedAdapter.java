package com.zeroone.herb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ViewHolder> {

    private List<FeaturedMovie> featuredMovies;
    private Context context;

    public FeaturedAdapter(List<FeaturedMovie> featuredMovies, Context context) {
        this.featuredMovies = featuredMovies;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeaturedMovie featuredMovie = featuredMovies.get(position);

        // Load images using Glide
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholderlogo);

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(featuredMovie.getfThumbUrl())
                .into(holder.imageView);

        holder.movieTitle.setText(featuredMovie.getTitle());

        String coverUrl = featuredMovie.getfCoverUrl();
        String desUrl = featuredMovie.getfDesUrl();
        String thumbUrl = featuredMovie.getfThumbUrl();
        String title = featuredMovie.getTitle();
        String trailerUrl = featuredMovie.getVideoUrl();
        String mName = featuredMovie.getfCoverName();
        String mActress = featuredMovie.getReleaseDate();
        String tlink = featuredMovie.getLink();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("coverURL", coverUrl);
                intent.putExtra("desURL", desUrl);
                intent.putExtra("thumbURL", thumbUrl);
                intent.putExtra("title", title);
                intent.putExtra("trailerURL", trailerUrl);
                intent.putExtra("mName", mName);
                intent.putExtra("mActress", mActress);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return featuredMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView movieTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            movieTitle = itemView.findViewById(R.id.movie_title);
        }
    }
}
