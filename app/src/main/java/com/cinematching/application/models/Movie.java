package com.cinematching.application.models;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by meryl on 10/10/2017.
 */

public class Movie {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @Exclude
    @Nullable
    private Uri imageUrl;
    @SerializedName("release_date")
    private ReleaseDate releaseDate;
    @SerializedName("genres")
    @Exclude
    @Nullable
    private List<String> genres;

    public Movie() {
    }

    public Movie(int id, String title, String overview, ReleaseDate releaseDate) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Movie(int id, String title, String overview, ReleaseDate releaseDate, @Nullable List<String> genres) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Nullable
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(@Nullable List<String> genres) {
        this.genres = genres;
    }

    @Nullable
    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "MovieApiResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                '}';
    }

}
