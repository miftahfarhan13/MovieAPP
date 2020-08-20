package com.example.moviecatalogueuiux.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.adapter.MovieAdapter;
import com.example.moviecatalogueuiux.adapter.TvAdapter;
import com.example.moviecatalogueuiux.model.MovieModel;
import com.example.moviecatalogueuiux.model.TvShowModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearhFragment extends Fragment {
    private ArrayList<MovieModel> movieModels = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private TvAdapter tvAdapter;
    private RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

    public SearhFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.rv_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            if (bundle.getParcelableArrayList("movieArray") != null){
                ArrayList<MovieModel> movieModels = bundle.getParcelableArrayList("movieArray");
                movieAdapter = new MovieAdapter(getContext(), movieModels);
                recyclerView.setAdapter(movieAdapter);
            }

            if (bundle.getParcelableArrayList("tvShowArray") != null){
                ArrayList<TvShowModel> tvShowModels = bundle.getParcelableArrayList("tvShowArray");
                tvAdapter = new TvAdapter(getContext(), tvShowModels);
                recyclerView.setAdapter(tvAdapter);
            }

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        movieModels.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        movieModels.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieModels.clear();
    }


}
