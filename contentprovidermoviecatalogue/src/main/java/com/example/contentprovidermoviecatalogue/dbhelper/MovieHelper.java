package com.example.contentprovidermoviecatalogue.dbhelper;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contentprovidermoviecatalogue.activity.MainActivity;
import com.example.contentprovidermoviecatalogue.model.MovieModel;


import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.TABLE_MOVIE;


public class MovieHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    public SQLiteDatabase database;
    private static MovieHelper INSTANCE;
    private static final String DATABASE_TABLE = TABLE_MOVIE;

    public MovieHelper (Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }



    public boolean check(String name){
        boolean check = false;

        Cursor cursor = database.rawQuery("SELECT " +  NAME  + " FROM " + TABLE_MOVIE + " WHERE " + NAME + " = '" + name + "'", null);
        if (cursor.moveToFirst()){
            do {
                String name2 = cursor.getString(cursor.getColumnIndex(NAME));
                if (name.equals(name2)){
                    check = true;
                    Log.i("MOVIENAME", name2);
                }else {
                    check = false;
                }
            }while (cursor.moveToNext());
        }

        Log.i("check", String.valueOf(check));
        return check;
    }


    public long insert(MovieModel movieModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(PHOTO, movieModel.getPhoto());
        initialValues.put(NAME, movieModel.getName());
        initialValues.put(DESCRIPTION, movieModel.getDescription());
        initialValues.put(RATE, movieModel.getRate());
        initialValues.put(RELEASE_DATE, movieModel.getReleasedate());
        return database.insert(TABLE_MOVIE, null, initialValues);
    }


    public int update(MovieModel movieModel) {
        ContentValues args = new ContentValues();
        args.put(PHOTO, movieModel.getPhoto());
        args.put(NAME, movieModel.getName());
        args.put(DESCRIPTION, movieModel.getDescription());
        args.put(RATE, movieModel.getRate());
        args.put(RELEASE_DATE, movieModel.getReleasedate());
        return database.update(TABLE_MOVIE, args, _ID + "= '" + movieModel.getId() + "'", null);
    }


    public int delete(String name) {
        return database.delete(TABLE_MOVIE, NAME + " = '" + name + "'", null);
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }


}
