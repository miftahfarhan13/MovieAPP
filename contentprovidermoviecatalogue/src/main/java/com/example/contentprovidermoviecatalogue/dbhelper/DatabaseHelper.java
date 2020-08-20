package com.example.contentprovidermoviecatalogue.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.TABLE_MOVIE;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_MOVIE = "create table " + TABLE_MOVIE +
            " (" + _ID + " integer primary key autoincrement, " +
            PHOTO + " text not null, " +
            NAME + " text not null, " +
            DESCRIPTION + " text not null, " +
            RATE + " text not null, " +
            RELEASE_DATE + " text not null);";


    private static String DATABASE_NAME = "dbfavorite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
}
