package com.example.contentprovidermoviecatalogue.adapter;



import android.app.Activity;
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
import com.example.contentprovidermoviecatalogue.R;
import com.example.contentprovidermoviecatalogue.activity.DetailActivity;
import com.example.contentprovidermoviecatalogue.dbhelper.MovieHelper;
import com.example.contentprovidermoviecatalogue.model.MovieModel;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    private ArrayList<MovieModel> movieModels = new ArrayList<>();
    Context context;
    private MovieHelper movieHelper;


    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setListMovie(ArrayList<MovieModel> movieModels) {
        this.movieModels.clear();
        this.movieModels.addAll(movieModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        viewHolder.tvName.setText(movieModels.get(viewHolder.getAdapterPosition()).getName());
        viewHolder.tvRate.setText(movieModels.get(viewHolder.getAdapterPosition()).getRate());
        viewHolder.tvDate.setText(movieModels.get(viewHolder.getAdapterPosition()).getReleasedate());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movieModels.get(viewHolder.getAdapterPosition()).getPhoto()).into(viewHolder.ivMovie);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieModel movieModel1 = new MovieModel();
                movieModel1.setId(movieModels.get(position).getId());
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

    public void addItem(ArrayList<MovieModel> mData) {
        this.movieModels = mData;
        notifyDataSetChanged();
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

