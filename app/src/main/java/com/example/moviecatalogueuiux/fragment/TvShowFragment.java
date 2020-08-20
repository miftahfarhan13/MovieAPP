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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviecatalogueuiux.adapter.TvAdapter;
import com.example.moviecatalogueuiux.api.APIClient;
import com.example.moviecatalogueuiux.api.APIInterface;
import com.example.moviecatalogueuiux.global_variable.global;
import com.example.moviecatalogueuiux.model.TvShowModel;
import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.response.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    public static int PAGE = 1;
    public static  String LANGUAGE = "en-us";
    public static String API_KEY = "312582920fd7603b69f03439e144b0dc";
    public static  String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ArrayList<TvShowModel> tvShowModels = new ArrayList<>();
    private TvAdapter tvAdapter;
    private ProgressBar progress;
    private RecyclerView recyclerView;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_tv_show,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        global.fragment_tag ="tvshow";
        recyclerView = view.findViewById(R.id.rv_tv);
        progress = view.findViewById(R.id.progressBar);
        getListTvShow();
    }

    private void getListTvShow(){

        APIInterface apiService =
                APIClient.getClient()
                        .create(APIInterface.class);

        Call<TvShowResponse> getListTvShow = apiService.getListTvShow(API_KEY, LANGUAGE, PAGE );
        getListTvShow.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (isAdded()){
                    Toast toast = Toast.makeText(getActivity(), "Berhasil",Toast.LENGTH_LONG);
                    toast.show();
                    TvShowResponse tvShowResponse = response.body();
                    List<TvShowResponse.Data> getData = tvShowResponse.getDataList();
                    if (getData != null){
                        for (int i = 0 ; i<getData.size() ; i++){
                            String movieTitl = getData.get(i).getOriginal_name();
                            String desc = getData.get(i).getOverview();
                            String date = getData.get(i).getFirst_air_date();
                            String photo = getData.get(i).getPoster_path();
                            String rate = getData.get(i).getVote_average();
                            TvShowModel tvShowModel = new TvShowModel(photo, movieTitl, desc, rate, date);
                            tvShowModels.add(tvShowModel);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView = getView().findViewById(R.id.rv_tv);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        TvAdapter tvAdapter = new TvAdapter(getContext(), tvShowModels);
                        recyclerView.setAdapter(tvAdapter);
                        progress.setVisibility(View.GONE);
                    }else {
                        Toast toast2 = Toast.makeText(getActivity(), "Null",Toast.LENGTH_LONG);
                        toast2.show();
                        progress.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("tvShowModels", tvShowModels);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            tvShowModels = savedInstanceState.getParcelableArrayList("tvShowModels");
            tvAdapter = new TvAdapter(getActivity(), tvShowModels);
            recyclerView.setAdapter(tvAdapter);
            progress.setVisibility(View.GONE);

        }

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            tvShowModels = savedInstanceState.getParcelableArrayList("tvShowModels");
            tvAdapter = new TvAdapter(getActivity(), tvShowModels);
            recyclerView.setAdapter(tvAdapter);
            progress.setVisibility(View.GONE);

        }
    }
}
