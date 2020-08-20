package com.example.contentprovidermoviecatalogue.activity;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.contentprovidermoviecatalogue.LoadMovieCallBack;
import com.example.contentprovidermoviecatalogue.R;
import com.example.contentprovidermoviecatalogue.adapter.MovieAdapter;
import com.example.contentprovidermoviecatalogue.dbhelper.MovieHelper;
import com.example.contentprovidermoviecatalogue.model.MovieModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import static com.example.contentprovidermoviecatalogue.MappingHelper.mapCursorToArrayList;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements LoadMovieCallBack {
    private Cursor cursor2;
    private MovieAdapter movieAdapter;
    RecyclerView recyclerView;
    public MovieHelper movieHelper;
    private DataObserver myObserver;

    public static Context contextOfApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_movie);

        movieAdapter = new MovieAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);
        contextOfApplication = getApplicationContext();

        new getData(this, this).execute();

    }



    @Override
    public void postExecute(Cursor notes) {

        ArrayList<MovieModel> listNotes = mapCursorToArrayList(notes);
        if (listNotes.size() > 0) {
            movieAdapter.setListMovie(listNotes);
        } else {
            Toast.makeText(this, "Tidak Ada data saat ini", Toast.LENGTH_SHORT).show();
            movieAdapter.setListMovie(new ArrayList<MovieModel>());
        }
    }

    private static class getData extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadMovieCallBack> weakCallback;


        private getData(Context context, LoadMovieCallBack callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);
        }

    }

    static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new getData(context, (MainActivity) context).execute();
        }
    }
}
