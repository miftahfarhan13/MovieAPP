package com.example.moviecatalogueuiux.activity;

import android.content.ContentValues;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviecatalogueuiux.adapter.MovieAdapter;
import com.example.moviecatalogueuiux.model.MovieModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.moviecatalogueuiux.model.TvShowModel;
import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.dbhelper.MovieHelper;
import com.example.moviecatalogueuiux.dbhelper.TvShowHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.CONTENT_URI;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;

public class DetailActivity extends AppCompatActivity {
    public static final String ITEM_MOVIE = "item_movie";
    @BindView(R.id.tv_nameDetail) TextView tvNameDetail;
    @BindView(R.id.tv_dateDetail) TextView tvDateDetail;
    @BindView(R.id.tv_rateDetail) TextView tvRateDetail;
    @BindView(R.id.tv_descDetail) TextView tvDescDetail;
    @BindView(R.id.iv_photoDetail) ImageView ivPhotoDetail;
    @BindView(R.id.btn_favoritTidak)
    Button btnFavoritTidak;
    @BindView(R.id.btn_favoritYa)
    Button btnFavoritYa;

    public MovieHelper movieHelper;
    public TvShowHelper tvShowHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        movieHelper = new MovieHelper(this);
        tvShowHelper = new TvShowHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            MovieModel movieModel = extras.getParcelable("object");
            TvShowModel tvShowModel = extras.getParcelable("object2");
            if (movieModel !=null){
                Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movieModel.getPhoto()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        ivPhotoDetail.setBackground(resource);
                    }
                });
                tvNameDetail.setText(movieModel.getName());
                tvDateDetail.setText(movieModel.getReleasedate());
                tvRateDetail.setText(movieModel.getRate());
                tvDescDetail.setText(movieModel.getDescription());

                movieHelper.open();
                Boolean isFavorite = movieHelper.check(tvNameDetail.getText().toString().trim());
                if (isFavorite == true){
                    btnFavoritYa.setVisibility(View.VISIBLE);
                    btnFavoritTidak.setVisibility(View.GONE);
                }else {
                    btnFavoritTidak.setVisibility(View.VISIBLE);
                    btnFavoritYa.setVisibility(View.GONE);
                }
                movieHelper.close();

                btnFavoritTidak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertDataMovie();
                        btnFavoritTidak.setVisibility(View.GONE);
                        btnFavoritYa.setVisibility(View.VISIBLE);
                    }
                });

                btnFavoritYa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle extras = getIntent().getExtras();
                        MovieModel movieModel = extras.getParcelable("object");
                        deleteitemMovie(movieModel.getName());
                        btnFavoritTidak.setVisibility(View.VISIBLE);
                        btnFavoritYa.setVisibility(View.GONE);
                    }
                });
            }

            else if (tvShowModel !=null){
                Glide.with(this).load("https://image.tmdb.org/t/p/w500"+tvShowModel.getPhotoTv()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        ivPhotoDetail.setBackground(resource);
                    }
                });
                tvNameDetail.setText(tvShowModel.getNameTv());
                tvDateDetail.setText(tvShowModel.getReleasedateTv());
                tvRateDetail.setText(tvShowModel.getRateTv());
                tvDescDetail.setText(tvShowModel.getDescriptionTv());

                tvShowHelper.open();
                Boolean isFavorite = tvShowHelper.check(tvNameDetail.getText().toString().trim());
                if (isFavorite == true){
                    btnFavoritYa.setVisibility(View.VISIBLE);
                    btnFavoritTidak.setVisibility(View.GONE);
                }else {
                    btnFavoritTidak.setVisibility(View.VISIBLE);
                    btnFavoritYa.setVisibility(View.GONE);
                }
                tvShowHelper.close();

                btnFavoritTidak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertDataTvShow();
                        btnFavoritTidak.setVisibility(View.GONE);
                        btnFavoritYa.setVisibility(View.VISIBLE);
                    }
                });

                btnFavoritYa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle extras = getIntent().getExtras();
                        TvShowModel tvShowModel1 = extras.getParcelable("object2");
                        deleteitemTvShow(tvShowModel1.getNameTv());
                        btnFavoritTidak.setVisibility(View.VISIBLE);
                        btnFavoritYa.setVisibility(View.GONE);
                    }
                });
            }
        }
    }


    private void deleteitemMovie(String name) {
        movieHelper.open();
        movieHelper.delete(name);
        movieHelper.close();
        Toast.makeText(getApplicationContext(), "Telah dihapus dari daftar Favorit", Toast.LENGTH_SHORT).show();

    }

    private void deleteitemTvShow(String name){
        tvShowHelper.open();
        tvShowHelper.delete(name);
        tvShowHelper.close();
        Toast.makeText(getApplicationContext(), "Telah dihapus dari daftar Favorit", Toast.LENGTH_SHORT).show();
    }

    private void insertDataMovie() {
        Bundle extras = getIntent().getExtras();
        MovieModel movieModel = extras.getParcelable("object");
        movieHelper.open();
        MovieModel movieModel1 = new MovieModel(movieModel.getPhoto(), tvNameDetail.getText().toString(), tvDescDetail.getText().toString(), tvRateDetail.getText().toString(), tvDateDetail.getText().toString());

        movieHelper.insert(movieModel1);

        Toast.makeText(getApplicationContext(), "Berhasil ditambahkan pada daftar Favorit", Toast.LENGTH_SHORT).show();
        movieHelper.close();
    }

    private void insertDataTvShow(){
        Bundle extras = getIntent().getExtras();
        TvShowModel tvShowModel = extras.getParcelable("object2");
        tvShowHelper.open();
        TvShowModel tvShowModel1 = new TvShowModel(tvShowModel.getPhotoTv(), tvNameDetail.getText().toString(), tvDescDetail.getText().toString(), tvRateDetail.getText().toString(), tvDateDetail.getText().toString());

        tvShowHelper.insert(tvShowModel1);
        Toast.makeText(getApplicationContext(), "Berhasil ditambahkan pada daftar Favorit", Toast.LENGTH_SHORT).show();
        tvShowHelper.close();
    }
}
