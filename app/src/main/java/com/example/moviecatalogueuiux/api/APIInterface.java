package com.example.moviecatalogueuiux.api;

import com.example.moviecatalogueuiux.response.MovieResponse;
import com.example.moviecatalogueuiux.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/3/movie/popular")
    Call<MovieResponse> getListMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);

    @GET("/3/tv/popular")
    Call<TvShowResponse> getListTvShow(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);

    @GET("/3/search/movie")
    Call<MovieResponse> searchMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("/3/search/tv")
    Call<TvShowResponse> searchTvShow(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("/3/discover/movie")
    Call<MovieResponse> getTodayMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("primary_release_date.gte") String primary_release_date_gte,
            @Query("primary_release_date.lte") String primary_release_date_lte
    );


}
