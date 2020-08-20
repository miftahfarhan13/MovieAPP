package com.example.moviecatalogueuiux.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.moviecatalogueuiux.dbhelper.DatabaseContract;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.getColumnInt;
import static com.example.moviecatalogueuiux.dbhelper.DatabaseContract.getColumnString;

public class MovieModel implements Parcelable {
    int id;
    private String photo;
    private String name;
    private String description;
    private String rate;
    private String releasedate;

    public String getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<MovieModel> getCREATOR() {
        return CREATOR;
    }

    public MovieModel(String photo, String name, String description, String rate, String releasedate) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.releasedate = releasedate;
    }

    public MovieModel(int id, String photo, String name, String description, String rate, String releasedate) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.releasedate = releasedate;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.rate);
        dest.writeString(this.releasedate);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.photo = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.rate = in.readString();
        this.releasedate = in.readString();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };


    public MovieModel(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.photo = getColumnString(cursor, DatabaseContract.MovieColumns.PHOTO);
        this.name = getColumnString(cursor, DatabaseContract.MovieColumns.NAME);
        this.description = getColumnString(cursor, DatabaseContract.MovieColumns.DESCRIPTION);
        this.rate = getColumnString(cursor, DatabaseContract.MovieColumns.RATE);
        this.releasedate = getColumnString(cursor, DatabaseContract.MovieColumns.RELEASE_DATE);

    }
}
