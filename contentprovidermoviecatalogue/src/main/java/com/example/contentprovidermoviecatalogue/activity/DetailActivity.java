package com.example.contentprovidermoviecatalogue.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.contentprovidermoviecatalogue.R;
import com.example.contentprovidermoviecatalogue.model.MovieModel;

public class DetailActivity extends AppCompatActivity {
    TextView tv_nameDetail;
    TextView tv_dateDetail;
    TextView tv_rateDetail;
    TextView tv_descDetail;
    ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_nameDetail = findViewById(R.id.tv_nameDetail);
        tv_dateDetail = findViewById(R.id.tv_dateDetail);
        tv_rateDetail = findViewById(R.id.tv_rateDetail);
        tv_descDetail = findViewById(R.id.tv_descDetail);
        iv_photo = findViewById(R.id.iv_photoDetail);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            MovieModel movieModel = extras.getParcelable("object");
            if (movieModel !=null){
                Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movieModel.getPhoto()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        iv_photo.setBackground(resource);
                    }
                });
                tv_nameDetail.setText(movieModel.getName());
                tv_dateDetail.setText(movieModel.getReleasedate());
                tv_rateDetail.setText(movieModel.getRate());
                tv_descDetail.setText(movieModel.getDescription());

            }
        }
    }
}
