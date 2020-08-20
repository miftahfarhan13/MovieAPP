package com.example.contentprovidermoviecatalogue.model;



import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract;

import static android.provider.BaseColumns._ID;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.NAME;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.PHOTO;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RATE;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.getColumnInt;
import static com.example.contentprovidermoviecatalogue.dbhelper.DatabaseContract.getColumnString;

public class MovieModel implements Parcelable {
    int id;
    String photo;
    String name;
    String description;
    String rate;
    String releasedate;

    public String getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dest.writeInt(this.id);
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.rate);
        dest.writeString(this.releasedate);
    }
    public MovieModel() {
    }

    public MovieModel(int id, String photo, String name, String description, String rate, String releasedate) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.releasedate = releasedate;
    }

    protected MovieModel(Parcel in) {
        this.id = in.readInt();
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
}
