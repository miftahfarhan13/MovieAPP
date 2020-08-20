package com.example.moviecatalogueuiux.helper;

import android.database.Cursor;

import com.example.moviecatalogueuiux.dbhelper.DatabaseContract;
import com.example.moviecatalogueuiux.model.MovieModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.getColumnString;

public class MappingHelper {
    public static ArrayList<MovieModel> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<MovieModel> movieModels = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID));
            String photo = notesCursor.getString(notesCursor.getColumnIndexOrThrow(PHOTO));
            String name = notesCursor.getString(notesCursor.getColumnIndexOrThrow(NAME));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DESCRIPTION));
            String rate = notesCursor.getString(notesCursor.getColumnIndexOrThrow(RATE));
            String releasedate = notesCursor.getString(notesCursor.getColumnIndexOrThrow(RELEASE_DATE));
            movieModels.add(new MovieModel(id, photo,name, description, rate, releasedate));

        }
        return movieModels;
    }
}
