package com.example.moviecatalogueuiux.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.dbhelper.MovieHelper;
import com.example.moviecatalogueuiux.dbhelper.TvShowHelper;
import com.example.moviecatalogueuiux.model.MovieModel;
import com.example.moviecatalogueuiux.model.TvShowModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory  {
    public MovieHelper movieHelper;
    public TvShowHelper tvShowHelper;
    private final ArrayList<Bitmap> mWidgetItems = new ArrayList<>();

    private final Context mContext;

    public StackRemoteViewsFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        movieHelper = new MovieHelper(mContext);
        tvShowHelper = new TvShowHelper(mContext);
    }

    @Override
    public void onDataSetChanged() {
        movieHelper.open();
        tvShowHelper.open();
        ArrayList<MovieModel> movieModels = movieHelper.getAllData();
        for (int i = 0 ; i<movieModels.size() ; i++){
            try {
                URL urlPoster = new URL("https://image.tmdb.org/t/p/w500"+movieModels.get(i).getPhoto());
                mWidgetItems.add(BitmapFactory.decodeStream(urlPoster.openConnection().getInputStream()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayList<TvShowModel>tvShowModels = tvShowHelper.getAllData();
        for (int i = 0 ; i<tvShowModels.size() ; i++){
            try {
                URL urlPoster = new URL("https://image.tmdb.org/t/p/w500"+tvShowModels.get(i).getPhotoTv());
                mWidgetItems.add(BitmapFactory.decodeStream(urlPoster.openConnection().getInputStream()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        movieHelper.close();
        tvShowHelper.close();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.iv_widget, mWidgetItems.get(position));
        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.iv_widget, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
