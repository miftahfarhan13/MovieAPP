package com.example.moviecatalogueuiux.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.api.APIClient;
import com.example.moviecatalogueuiux.api.APIInterface;
import com.example.moviecatalogueuiux.fragment.FavoriteFragment;
import com.example.moviecatalogueuiux.fragment.MovieFragment;
import com.example.moviecatalogueuiux.fragment.SearhFragment;
import com.example.moviecatalogueuiux.fragment.TvShowFragment;
import com.example.moviecatalogueuiux.global_variable.global;
import com.example.moviecatalogueuiux.model.MovieModel;
import com.example.moviecatalogueuiux.model.TvShowModel;
import com.example.moviecatalogueuiux.notification.AlarmReceiver;
import com.example.moviecatalogueuiux.response.MovieResponse;
import com.example.moviecatalogueuiux.response.TvShowResponse;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.MovieColumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MovieModel> movieModels = new ArrayList<>();
    private ArrayList<TvShowModel> tvShowModels = new ArrayList<>();
    private long backPressedTime;
    private final String SIMPLE_FRAGMENT_TAG = "myfragmenttag";
    private SearchView searchView;
    public static int PAGE = 1;
    public static  String LANGUAGE = "en-us";
    public static String API_KEY = "312582920fd7603b69f03439e144b0dc";
    private static HandlerThread handlerThread;
    

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieFragment();
                    loadFragment(fragment);
                    break;
                case R.id.navigation_tvshow:
                    fragment = new TvShowFragment();
                    loadFragment(fragment);
                    break;
                case R.id.navigation_favourite:
                    fragment = new FavoriteFragment();
                    loadFragment(fragment);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment, SIMPLE_FRAGMENT_TAG).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());


        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState!=null){
            getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_TAG);
        }else if (savedInstanceState == null){
            Fragment fragment;
            fragment = new MovieFragment();
            loadFragment(fragment);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s != null){
                    if (global.fragment_tag.equals("movie")){
                        searchMovie(s);
                    }
                    if (global.fragment_tag.equals("tvshow")){
                        searchTvShow(s);
                    }
                    if (global.fragment_tag.equals("favorit")){

                    }

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void onBackPressed(){

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast toast = Toast.makeText(MainActivity.this, "Tekan Sekali Untuk Keluar",Toast.LENGTH_LONG);
            toast.show();        }
        backPressedTime = System.currentTimeMillis();

    }

    private boolean loadFragment(Fragment fragment) {
//switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, SIMPLE_FRAGMENT_TAG)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }if (item.getItemId() == R.id.notification_setting) {
            Intent mIntent = new Intent(MainActivity.this, SettingAlarmActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void searchMovie(final String search){
        if (search != null){
            movieModels.clear();
            APIInterface apiService =
                    APIClient.getClient()
                            .create(APIInterface.class);
            Call<MovieResponse> searchMovie = apiService.searchMovie(API_KEY, LANGUAGE, search);
            searchMovie.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Berhasil",Toast.LENGTH_LONG);
                    toast.show();
                    String search2 = search.substring(0,1);
                    MovieResponse movieResponses = response.body();
                    if (movieResponses != null){
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

                            final Bundle arguments = new Bundle();
                            arguments.putParcelableArrayList("movieArray", movieModels);
                            Fragment fragment = null;
                            fragment = new SearhFragment();
                            fragment.setArguments(arguments);
                            loadFragment(fragment);
                        }
                    }else {
                        Fragment fragment = null;
                        fragment = new MovieFragment();
                        loadFragment(fragment);
                    }
                }


                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                }
            });
        }
    }

    public void searchTvShow(String search){
        tvShowModels.clear();
        APIInterface apiService =
                APIClient.getClient()
                        .create(APIInterface.class);
        Call<TvShowResponse> searchTvShow = apiService.searchTvShow(API_KEY, LANGUAGE, search);
        searchTvShow.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                Toast toast = Toast.makeText(getApplicationContext(), "Berhasil",Toast.LENGTH_LONG);
                toast.show();
                TvShowResponse tvShowResponse = response.body();
                if (tvShowResponse != null){
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

                        final Bundle arguments = new Bundle();
                        arguments.putParcelableArrayList("tvShowArray", tvShowModels);
                        Fragment fragment = null;
                        fragment = new SearhFragment();
                        fragment.setArguments(arguments);
                        loadFragment(fragment);
                    }
                }else {
                    Fragment fragment = null;
                    fragment = new TvShowFragment();
                    loadFragment(fragment);
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

            }
        });
    }
}