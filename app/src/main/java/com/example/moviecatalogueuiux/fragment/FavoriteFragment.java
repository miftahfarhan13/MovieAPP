package com.example.moviecatalogueuiux.fragment;


import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviecatalogueuiux.adapter.MovieAdapter;
import com.example.moviecatalogueuiux.adapter.TvAdapter;
import com.example.moviecatalogueuiux.global_variable.global;
import com.example.moviecatalogueuiux.model.MovieModel;
import com.example.moviecatalogueuiux.model.TvShowModel;
import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.dbhelper.MovieHelper;
import com.example.moviecatalogueuiux.dbhelper.TvShowHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    public MovieHelper movieHelper;
    public MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    private TextView tvBelumFavoritMovie;

    public TvShowHelper tvShowHelper;
    public TvAdapter tvAdapter;
    private RecyclerView rvTvShow;
    private TextView tvBelumFavoritTvShow;
//    private DataObserver myObserver;
    private static HandlerThread handlerThread;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        global.fragment_tag = "favorite";
        rvMovie = view.findViewById(R.id.rv_movie_favorit);
        rvTvShow = view.findViewById(R.id.rv_tv_favorit);
        tvBelumFavoritTvShow = view.findViewById(R.id.tv_belumFavoritTvShow);
        tvBelumFavoritMovie = view.findViewById(R.id.tv_belumFavoritMovie);

        rvTvShow.setHasFixedSize(true);
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

//        handlerThread = new HandlerThread("DataObserver");
//        handlerThread.start();
//        Handler handler = new Handler(handlerThread.getLooper());
//        myObserver = new DataObserver(handler, getActivity().getApplicationContext());
//        getActivity().getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);



        if (isAdded()){
            movieHelper = new MovieHelper(getActivity().getApplicationContext());
            tvShowHelper = new TvShowHelper(getActivity().getApplicationContext());
        }

        getAllDataMovie();
        getAllDataTvShow();
    }

    private void getAllDataMovie() {
        movieHelper.open();
        ArrayList<MovieModel> movieModels = movieHelper.getAllData();
        if (movieModels.size() > 0){
            movieHelper.close();
            movieAdapter = new MovieAdapter(getActivity().getApplicationContext(), movieModels);
            movieAdapter.addItem(movieModels);
            rvMovie.setVisibility(View.VISIBLE);
            rvMovie.setAdapter(movieAdapter);
            tvBelumFavoritMovie.setVisibility(View.GONE);
        }else {
            tvBelumFavoritMovie.setVisibility(View.VISIBLE);
            rvMovie.setVisibility(View.GONE);
        }

    }

    private void getAllDataTvShow() {
        tvShowHelper.open();
        ArrayList<TvShowModel> tvShowModels = tvShowHelper.getAllData();
        if (tvShowModels.size() > 0){
            tvShowHelper.close();
            tvAdapter = new TvAdapter(getActivity().getApplicationContext(), tvShowModels);
            tvAdapter.addItem(tvShowModels);
            rvTvShow.setVisibility(View.VISIBLE);
            rvTvShow.setAdapter(tvAdapter);
            tvBelumFavoritTvShow.setVisibility(View.GONE);
        }else {
            tvBelumFavoritTvShow.setVisibility(View.VISIBLE);
            rvTvShow.setVisibility(View.GONE);
        }

    }

//    public static class DataObserver extends ContentObserver {
//
//        final Context context;
//
//        public DataObserver(Handler handler, Context context) {
//            super(handler);
//            this.context = context;
//        }
//
//        @Override
//        public void onChange(boolean selfChange) {
//            super.onChange(selfChange);
//            new LoadNoteAsync(context, (LoadNotesCallBack) context).execute();
//
//        }
//    }
//
//    private static class LoadNoteAsync extends AsyncTask<Void, Void, Cursor> {
//
//        private final WeakReference<Context> weakContext;
//        private final WeakReference<LoadNotesCallBack> weakCallback;
//
//        private LoadNoteAsync(Context context, LoadNotesCallBack callback) {
//            weakContext = new WeakReference<>(context);
//            weakCallback = new WeakReference<>(callback);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            weakCallback.get().preExecute();
//        }
//
//        @Override
//        protected Cursor doInBackground(Void... voids) {
//            Context context = weakContext.get();
//            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
//        }
//
//        @Override
//        protected void onPostExecute(Cursor notes) {
//            super.onPostExecute(notes);
//            weakCallback.get().postExecute(notes);
//        }
//    }

}
