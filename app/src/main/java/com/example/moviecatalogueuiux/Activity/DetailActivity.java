package com.example.moviecatalogueuiux.Activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.moviecatalogueuiux.Model.MovieModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.moviecatalogueuiux.Model.TvShowModel;
import com.example.moviecatalogueuiux.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_nameDetail) TextView tv_nameDetail;
    @BindView(R.id.tv_dateDetail) TextView tv_dateDetail;
    @BindView(R.id.tv_rateDetail) TextView tv_rateDetail;
    @BindView(R.id.tv_descDetail) TextView tv_descDetail;
    @BindView(R.id.iv_photoDetail) ImageView iv_photoDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            MovieModel movieModel = extras.getParcelable("object");
            TvShowModel tvShowModel = extras.getParcelable("object2");
            if (movieModel !=null){
                Glide.with(this).load(movieModel.getPhoto()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        iv_photoDetail.setBackground(resource);
                    }
                });
                tv_nameDetail.setText(movieModel.getName());
                tv_dateDetail.setText(movieModel.getReleasedate());
                tv_rateDetail.setText(movieModel.getRate());
                tv_descDetail.setText(movieModel.getDescription());
            }
            else if (tvShowModel !=null){
                Glide.with(this).load(tvShowModel.getPhotoTv()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        iv_photoDetail.setBackground(resource);
                    }
                });
                tv_nameDetail.setText(tvShowModel.getNameTv());
                tv_dateDetail.setText(tvShowModel.getReleasedateTv());
                tv_rateDetail.setText(tvShowModel.getRateTv());
                tv_descDetail.setText(tvShowModel.getDescriptionTv());
            }
        }


    }
}
