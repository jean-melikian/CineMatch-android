package com.cinematching.application.webservice.api.retrofit;


import com.cinematching.application.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by meryl on 08/10/2017.
 */

public interface MovieRetrofit {

    @GET("api/movies/{id}")
    Call<Movie> read(@Path(value = "id", encoded = true) String id);

    @GET("api/movies/screening")
    Call<List<Movie>> getSreening();

    @GET("api/movies/upcomming")
    Call<List<Movie>> getUpcomming();
}
