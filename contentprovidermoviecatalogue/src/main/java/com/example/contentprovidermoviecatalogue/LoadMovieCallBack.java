package com.example.contentprovidermoviecatalogue;

import android.database.Cursor;

public interface LoadMovieCallBack {


    void postExecute(Cursor notes);
}
