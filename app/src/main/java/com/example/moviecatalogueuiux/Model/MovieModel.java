package com.example.moviecatalogueuiux.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
    int photo;
    String name;
    String description;
    String rate;
    String releasedate;

    public int getPhoto() {
        return photo;
    }

    public MovieModel(int photo, String name, String description, String rate, String releasedate) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.releasedate = releasedate;
    }

    public void setPhoto(int photo) {
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
        dest.writeInt(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.rate);
        dest.writeString(this.releasedate);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.photo = in.readInt();
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
