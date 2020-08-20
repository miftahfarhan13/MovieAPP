package com.example.moviecatalogueuiux.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TABLE_MOVIE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TABLE_TV_SHOW;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.PHOTO_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.NAME_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.DESCRIPTION_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.RATE_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.RELEASE_DATE_TV;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_MOVIE = "create table " + TABLE_MOVIE +
            " (" + _ID + " integer primary key autoincrement, " +
            PHOTO + " text not null, " +
            NAME + " text not null, " +
            DESCRIPTION + " text not null, " +
            RATE + " text not null, " +
            RELEASE_DATE + " text not null);";

    public static String CREATE_TABLE_TV_SHOW = "create table " + TABLE_TV_SHOW +
            " (" + _ID + " integer primary key autoincrement, " +
            PHOTO_TV + " text not null, " +
            NAME_TV + " text not null, " +
            DESCRIPTION_TV + " text not null, " +
            RATE_TV + " text not null, " +
            RELEASE_DATE_TV + " text not null);";
    private static String DATABASE_NAME = "dbfavorite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);
        db.execSQL(CREATE_TABLE_TV_SHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TV_SHOW);
        onCreate(db);
    }
}
