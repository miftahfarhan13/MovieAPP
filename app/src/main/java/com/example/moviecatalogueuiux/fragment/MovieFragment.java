package com.example.moviecatalogueuiux.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviecatalogueuiux.api.APIClient;
import com.example.moviecatalogueuiux.api.APIInterface;
import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.global_variable.global;
import com.example.moviecatalogueuiux.model.MovieModel;
import com.example.moviecatalogueuiux.adapter.MovieAdapter;
import com.example.moviecatalogueuiux.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ArrayList<MovieModel> movieModels = new ArrayList<>();
    private MovieAdapter movieAdapter;
    public static int PAGE = 1;
    public static  String LANGUAGE = "en-us";
    public static String API_KEY = "312582920fd7603b69f03439e144b0dc";
    public static  String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500";

    private ProgressBar progress;
    private RecyclerView recyclerView;


    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        global.fragment_tag = "movie";
        recyclerView = getView().findViewById(R.id.rv_movie);
        progress = getView().findViewById(R.id.progressBar);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
            movieModels = savedInstanceState.getParcelableArrayList("movieModels");
            movieAdapter = new MovieAdapter(getActivity(), movieModels);
            recyclerView.setAdapter(movieAdapter);
            progress.setVisibility(View.GONE);

        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        getListMovie();
    }

    private void getListMovie(){
        APIInterface apiService =
                APIClient.getClient()
                        .create(APIInterface.class);

        Call<MovieResponse> getListMovie = apiService.getListMovie(API_KEY, LANGUAGE, PAGE );
        getListMovie.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (isAdded()){
                    Toast toast = Toast.makeText(getActivity(), "Berhasil",Toast.LENGTH_LONG);
                    toast.show();
                    MovieResponse movieResponses = response.body();
                    List<MovieResponse.Data> getData = movieResponses.getDataList();
                    if (getData != null){
                        for (int i = 0 ; i<getData.size() ; i++){
                            String movieTitl = getData.get(i).getOriginal_title();
                            String desc = getData.get(i).getOverview();
                            String date = getData.get(i).getRelease_date();
                            String photo = getData.get(i).getPoster_path();
                            String rate = getData.get(i).getVote_average();
                            MovieModel movieModel = new MovieModel(photo, movieTitl, desc, rate, date);
                            movieModels.add(movieModel);
                        }

                        movieAdapter = new MovieAdapter(getContext(), movieModels);
                        recyclerView.setAdapter(movieAdapter);
                        progress.setVisibility(View.GONE);
                    }else {
                        Toast toast2 = Toast.makeText(getActivity(), "Null",Toast.LENGTH_LONG);
                        toast2.show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getActivity(), "Gagal",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movieModels", movieModels);
        outState.putParcelable(LIST_STATE_KEY, recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
            movieModels = savedInstanceState.getParcelableArrayList("movieModels");
            movieAdapter = new MovieAdapter(getActivity(), movieModels);
            recyclerView.setAdapter(movieAdapter);
            progress.setVisibility(View.GONE);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null){
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            movieModels = savedInstanceState.getParcelableArrayList("movieModels");
            movieAdapter = new MovieAdapter(getActivity(), movieModels);
            recyclerView.setAdapter(movieAdapter);
            progress.setVisibility(View.GONE);

        }
    }
}
