package com.example.contentprovidermoviecatalogue.dbhelper;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "com.example.moviecatalogueuiux";
    public static String TABLE_MOVIE = "table_movie";
    private static final String SCHEME = "content";

    public static final class MovieColumns implements BaseColumns {
        public static String PHOTO = "photo";
        public static String NAME = "name";
        public static String DESCRIPTION = "description";
        public static String RATE = "rate";
        public static String RELEASE_DATE = "releasedate";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }



    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
