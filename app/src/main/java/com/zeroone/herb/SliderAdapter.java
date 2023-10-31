package com.zeroone.herb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.snapshot.Index;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    private List<FeaturedMovie> dataModels = new ArrayList<>();

    public void renewItems(List<FeaturedMovie> dataModels) {
        this.dataModels = dataModels;
        notifyDataSetChanged();
    }

    public void deleteItems(int position) {
        this.dataModels.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.trailer_title.setText(dataModels.get(position).getTitle());
        Glide.with(viewHolder.itemView).load(dataModels.get(position).getfCoverUrl()).into(viewHolder.slider_image);

        String coverUrl = dataModels.get(position).getfCoverUrl();
        String desUrl = dataModels.get(position).getfDesUrl();
        String thumbUrl = dataModels.get(position).getfThumbUrl();
        String title = dataModels.get(position).getTitle();
        String trailerUrl = dataModels.get(position).getVideoUrl();
        String mName = dataModels.get(position).getfCoverName();
        String mActress = dataModels.get(position).getReleaseDate();
        String tlink = dataModels.get(position).getLink();

        viewHolder.play_button.setOnClickListener(new View.OnClickListener() {
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
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView slider_image;
        TextView trailer_title;
        ImageButton play_button;

        public MyViewHolder(View itemView) {
            super(itemView);

            slider_image = itemView.findViewById(R.id.image_thumbnail);
            trailer_title = itemView.findViewById(R.id.trailer_title);
            play_button = itemView.findViewById(R.id.buttonGo);
        }
    }
}