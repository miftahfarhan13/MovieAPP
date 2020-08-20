package com.example.moviecatalogueuiux.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.moviecatalogueuiux.model.TvShowModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TABLE_MOVIE;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TABLE_TV_SHOW;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.DESCRIPTION_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.NAME_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.PHOTO_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.RATE_TV;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.TvShowColumns.RELEASE_DATE_TV;

public class TvShowHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    public SQLiteDatabase database;
    private static final String DATABASE_TABLE = TABLE_TV_SHOW;

    public TvShowHelper (Context context){
        this.context = context;
    }

    public TvShowHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<TvShowModel> getAllData() {
        Cursor cursor = database.query(TABLE_TV_SHOW, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<TvShowModel> arrayList = new ArrayList<>();
        TvShowModel tvShowModel;
        if (cursor.getCount() > 0) {
            do {

                tvShowModel = new TvShowModel();
                tvShowModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tvShowModel.setPhotoTv(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO_TV)));
                tvShowModel.setNameTv(cursor.getString(cursor.getColumnIndexOrThrow(NAME_TV)));
                tvShowModel.setDescriptionTv(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_TV)));
                tvShowModel.setRateTv(cursor.getString(cursor.getColumnIndexOrThrow(RATE_TV)));
                tvShowModel.setReleasedateTv(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE_TV)));
                arrayList.add(tvShowModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean check(String name){
        boolean check = false;

        Cursor cursor = database.rawQuery("SELECT " +  NAME_TV  + " FROM " + TABLE_TV_SHOW + " WHERE " + NAME_TV + " = '" + name + "'", null);
        if (cursor.moveToFirst()){
            do {
                String name2 = cursor.getString(cursor.getColumnIndex(NAME_TV));
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


    public long insert(TvShowModel tvShowModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(PHOTO_TV, tvShowModel.getPhotoTv());
        initialValues.put(NAME_TV, tvShowModel.getNameTv());
        initialValues.put(DESCRIPTION_TV, tvShowModel.getDescriptionTv());
        initialValues.put(RATE_TV, tvShowModel.getRateTv());
        initialValues.put(RELEASE_DATE_TV, tvShowModel.getReleasedateTv());
        return database.insert(TABLE_TV_SHOW, null, initialValues);
    }




    public int delete(String name) {
        return database.delete(TABLE_TV_SHOW, NAME_TV + " = '" + name + "'", null);
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
