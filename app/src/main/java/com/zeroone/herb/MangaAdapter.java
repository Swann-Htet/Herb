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

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.ViewHolder> {

    private List<Manga> mangas;
    private Context context;

    public MangaAdapter(List<Manga> mangas, Context context) {
        this.mangas = mangas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Manga manga = mangas.get(position);

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholderlogo);

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(manga.getmThumbUrl())
                .into(holder.imageView);

        holder.movieTitle.setText(manga.getMtitle());

        String coverUrl = manga.getmCoverUrl();
        String desUrl = manga.getmDesUrl();
        String thumbUrl = manga.getmThumbUrl();
        String title = manga.getMtitle();
        String mName = manga.getmCoverName();
        String mAuthor = manga.getMeleaseDate();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MangaDetailActivity.class);
                intent.putExtra("coverURL", coverUrl);
                intent.putExtra("desURL", desUrl);
                intent.putExtra("thumbURL", thumbUrl);
                intent.putExtra("title", title);
                intent.putExtra("mName", mName);
                intent.putExtra("mAuthor", mAuthor);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView movieTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewM);
            movieTitle = itemView.findViewById(R.id.mangaTitle);
        }
    }
}
