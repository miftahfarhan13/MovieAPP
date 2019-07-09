package com.example.moviecatalogueuiux.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecatalogueuiux.Activity.DetailActivity;
import com.example.moviecatalogueuiux.Model.MovieModel;
import com.example.moviecatalogueuiux.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieModel> movieModels;
    Context context;

    public MovieAdapter(Context context, ArrayList<MovieModel> movieModels) {
        this.context = context;
        this.movieModels = movieModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.tvName.setText(movieModels.get(position).getName());
        viewHolder.tvRate.setText(movieModels.get(position).getRate());
        viewHolder.tvDate.setText(movieModels.get(position).getReleasedate());
        Glide.with(context).load(movieModels.get(position).getPhoto()).into(viewHolder.ivMovie);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieModel movieModel1 = new MovieModel();
                movieModel1.setPhoto(movieModels.get(position).getPhoto());
                movieModel1.setName(movieModels.get(position).getName());
                movieModel1.setDescription(movieModels.get(position).getDescription());
                movieModel1.setReleasedate(movieModels.get(position).getReleasedate());
                movieModel1.setRate(movieModels.get(position).getRate());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", movieModel1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMovie;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.iv_photoListMovie);
            tvName = itemView.findViewById(R.id.tv_nameMovie);
            tvDate = itemView.findViewById(R.id.tv_dateMovie);
            tvRate = itemView.findViewById(R.id.tv_rateMovie);
        }
    }
}
