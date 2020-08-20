package com.example.contentprovidermoviecatalogue;

import android.database.Cursor;

import com.example.contentprovidermoviecatalogue.model.MovieModel;
import com.example.contentprovidermoviecatalogue.model.MovieModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;

public class MappingHelper {
    public static ArrayList<MovieModel> mapCursorToArrayList(Cursor movieCursor) {
        ArrayList<MovieModel> movieModels = new ArrayList<>();

        while (movieCursor.moveToNext()) {
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(_ID));
            String photo = movieCursor.getString(movieCursor.getColumnIndexOrThrow(PHOTO));
            String name = movieCursor.getString(movieCursor.getColumnIndexOrThrow(NAME));
            String description = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DESCRIPTION));
            String rate = movieCursor.getString(movieCursor.getColumnIndexOrThrow(RATE));
            String releasedate = movieCursor.getString(movieCursor.getColumnIndexOrThrow(RELEASE_DATE));
            movieModels.add(new MovieModel(id, photo, name, description, rate, releasedate));
        }

        return movieModels;
    }
}
