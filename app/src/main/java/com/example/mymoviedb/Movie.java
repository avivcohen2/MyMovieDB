package com.example.mymoviedb;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Parcelable, Serializable {
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("genre_ids")
    public List<Integer> genre_ids;
    @SerializedName("id")
    public int id;
    @SerializedName("original_language")
    public String original_language;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public double vote_average;
    @SerializedName("vote_count")
    public int vote_count;

    protected Movie(Parcel in) {
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        id = in.readInt();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
        vote_count = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(backdrop_path);
        dest.writeInt(id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(vote_average);
        dest.writeInt(vote_count);
    }
}









