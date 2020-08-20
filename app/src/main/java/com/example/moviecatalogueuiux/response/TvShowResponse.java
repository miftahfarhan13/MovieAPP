package com.example.moviecatalogueuiux.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    @Expose
    private List<TvShowResponse.Data> results;

    public List<TvShowResponse.Data> getDataList() {
        return results;
    }

    public void setDataList(List<TvShowResponse.Data> dataList) {
        this.results = dataList;
    }

    public static class Data{
        @SerializedName("original_name")
        @Expose
        private String original_name;

        @SerializedName("first_air_date")
        @Expose
        private String first_air_date;

        @SerializedName("vote_average")
        @Expose
        private String vote_average;

        @SerializedName("poster_path")
        @Expose
        private String poster_path;

        @SerializedName("overview")
        @Expose
        private String overview;

        public String getOriginal_name() {
            return original_name;
        }

        public void setOriginal_name(String original_name) {
            this.original_name = original_name;
        }

        public String getFirst_air_date() {
            return first_air_date;
        }

        public void setFirst_air_date(String first_air_date) {
            this.first_air_date = first_air_date;
        }

        public String getVote_average() {
            return vote_average;
        }

        public void setVote_average(String vote_average) {
            this.vote_average = vote_average;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }
    }
}
