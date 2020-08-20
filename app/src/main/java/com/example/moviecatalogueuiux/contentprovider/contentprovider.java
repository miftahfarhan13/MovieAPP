package com.example.moviecatalogueuiux.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;


import com.example.moviecatalogueuiux.dbhelper.DatabaseContract;
import com.example.moviecatalogueuiux.dbhelper.MovieHelper;
import com.example.moviecatalogueuiux.fragment.FavoriteFragment;

import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.AUTHORITY;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.CONTENT_URI;

public class contentprovider extends ContentProvider {


    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MovieHelper movieHelper;
    static {
        sUriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_MOVIE, MOVIE);
        sUriMatcher.addURI(AUTHORITY,
                DatabaseContract.TABLE_MOVIE + "/#",
                MOVIE_ID);
    }

    @Override
    public boolean onCreate() {
        movieHelper = MovieHelper.getInstance(getContext());
        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
        movieHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        return cursor;

    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri,  ContentValues values) {
        movieHelper.open();
        long added;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                added = movieHelper.insertProvider(values);
                break;
            default:
                added = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)){
            case MOVIE_ID:
                deleted = movieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        if (deleted > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        int updated;
        switch (sUriMatcher.match(uri)){
            case MOVIE_ID:
                updated = movieHelper.updateProvider(uri.getLastPathSegment(), values);
                break;
            default:
                updated = 0;
                break;
        }
        if (updated > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }
}
