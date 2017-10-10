package com.cinematching.application.webservice.api.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meryl on 10/10/2017.
 */

public class MovieApiResponse {
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("overview")
    String overview;
    @SerializedName("release_date")
    ArrayList<String> releaseDate;
    @SerializedName("genres")
    List<String> genres;

    public MovieApiResponse() {
    }

    public MovieApiResponse(int id, String title, String overview, ArrayList<String> releaseDate, List<String> genres) {
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

    public String getOverview(){
        return overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public ArrayList<String> getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(ArrayList<String> releaseDate){
        this.releaseDate = releaseDate;
    }

    public List<String>  getGenres(){
        return genres;
    }

    public void setGenres(List<String> genres){
        this.genres = genres;
    }

}
