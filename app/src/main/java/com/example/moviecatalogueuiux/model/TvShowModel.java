package com.example.moviecatalogueuiux.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowModel implements Parcelable {
    int id;
    String photoTv;
    String nameTv;
    String descriptionTv;
    String rateTv;
    String releasedateTv;

    public TvShowModel() {
    }

    public TvShowModel(String photoTv, String nameTv, String descriptionTv, String rateTv, String releasedateTv) {
        this.photoTv = photoTv;
        this.nameTv = nameTv;
        this.descriptionTv = descriptionTv;
        this.rateTv = rateTv;
        this.releasedateTv = releasedateTv;
    }

    public TvShowModel(int id, String photoTv, String nameTv, String descriptionTv, String rateTv, String releasedateTv) {
        this.id = id;
        this.photoTv = photoTv;
        this.nameTv = nameTv;
        this.descriptionTv = descriptionTv;
        this.rateTv = rateTv;
        this.releasedateTv = releasedateTv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<TvShowModel> getCREATOR() {
        return CREATOR;
    }

    public String getPhotoTv() {
        return photoTv;
    }

    public void setPhotoTv(String photoTv) {
        this.photoTv = photoTv;
    }

    public String getNameTv() {
        return nameTv;
    }

    public void setNameTv(String nameTv) {
        this.nameTv = nameTv;
    }

    public String getDescriptionTv() {
        return descriptionTv;
    }

    public void setDescriptionTv(String descriptionTv) {
        this.descriptionTv = descriptionTv;
    }

    public String getRateTv() {
        return rateTv;
    }

    public void setRateTv(String rateTv) {
        this.rateTv = rateTv;
    }

    public String getReleasedateTv() {
        return releasedateTv;
    }

    public void setReleasedateTv(String releasedateTv) {
        this.releasedateTv = releasedateTv;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photoTv);
        dest.writeString(this.nameTv);
        dest.writeString(this.descriptionTv);
        dest.writeString(this.rateTv);
        dest.writeString(this.releasedateTv);
    }

    protected TvShowModel(Parcel in) {
        this.photoTv = in.readString();
        this.nameTv = in.readString();
        this.descriptionTv = in.readString();
        this.rateTv = in.readString();
        this.releasedateTv = in.readString();
    }

    public static final Creator<TvShowModel> CREATOR = new Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel source) {
            return new TvShowModel(source);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };
}
