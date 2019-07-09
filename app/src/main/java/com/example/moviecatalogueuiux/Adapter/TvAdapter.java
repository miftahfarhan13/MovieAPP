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
import com.example.moviecatalogueuiux.Model.TvShowModel;
import com.example.moviecatalogueuiux.R;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {

    private ArrayList<TvShowModel> tvShowModels;
    Context context;

    public TvAdapter(Context context, ArrayList<TvShowModel> tvShowModels) {
        this.tvShowModels = tvShowModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tv_show, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.tvName.setText(tvShowModels.get(position).getNameTv());
        viewHolder.tvRate.setText(tvShowModels.get(position).getRateTv());
        viewHolder.tvDate.setText(tvShowModels.get(position).getReleasedateTv());
        Glide.with(context).load(tvShowModels.get(position).getPhotoTv()).into(viewHolder.ivTV);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvShowModel tvShowModel = new TvShowModel();
                tvShowModel.setPhotoTv(tvShowModels.get(position).getPhotoTv());
                tvShowModel.setNameTv(tvShowModels.get(position).getNameTv());
                tvShowModel.setDescriptionTv(tvShowModels.get(position).getDescriptionTv());
                tvShowModel.setReleasedateTv(tvShowModels.get(position).getReleasedateTv());
                tvShowModel.setRateTv(tvShowModels.get(position).getRateTv());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object2", tvShowModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivTV;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTV = itemView.findViewById(R.id.iv_photoListTv);
            tvName = itemView.findViewById(R.id.tv_nameTv);
            tvDate = itemView.findViewById(R.id.tv_dateTv);
            tvRate = itemView.findViewById(R.id.tv_rateTv);
        }
    }
}
