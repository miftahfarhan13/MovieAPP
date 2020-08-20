package com.example.moviecatalogueuiux.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private List<Data> results;

    public List<Data> getDataList() {
        return results;
    }

    public void setDataList(List<Data> dataList) {
        this.results = dataList;
    }

    public static class Data{
        @SerializedName("id")
        @Expose
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @SerializedName("original_title")
        @Expose
        private String original_title;

        @SerializedName("release_date")
        @Expose
        private String release_date;

        @SerializedName("vote_average")
        @Expose
        private String vote_average;

        @SerializedName("poster_path")
        @Expose
        private String poster_path;

        @SerializedName("overview")
        @Expose
        private String overview;

        public String getOriginal_title() {
            return original_title;
        }


        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getVote_average() {
            return vote_average;
        }


        public String getPoster_path() {
            return poster_path;
        }

        public String getOverview() {
            return overview;
        }





    }

}
