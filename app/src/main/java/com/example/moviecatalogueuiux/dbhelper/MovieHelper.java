package com.example.moviecatalogueuiux.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.moviecatalogueuiux.model.MovieModel;

import java.util.ArrayList;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TABLE_MOVIE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;
import static android.provider.BaseColumns._ID;

public class MovieHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    public SQLiteDatabase database;
    private static MovieHelper INSTANCE;
    private static final String DATABASE_TABLE = TABLE_MOVIE;

    public MovieHelper (Context context){
        this.context = context;
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<MovieModel> getAllData() {
        Cursor cursor = database.query(TABLE_MOVIE, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<MovieModel> arrayList = new ArrayList<>();
        MovieModel movieModel;
        if (cursor.getCount() > 0) {
            do {

                movieModel = new MovieModel();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movieModel.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO)));
                movieModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                movieModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                movieModel.setRate(cursor.getString(cursor.getColumnIndexOrThrow(RATE)));
                movieModel.setReleasedate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                arrayList.add(movieModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
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
