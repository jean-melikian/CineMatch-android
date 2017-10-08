package com.cinematching.application.webservice.api.retrofit;


import com.cinematching.application.models.Movie;
import com.cinematching.application.webservice.IServiceResultListener;
import com.cinematching.application.webservice.api.IMovieService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by meryl on 08/10/2017.
 */

public class MovieService implements IMovieService {

    private MovieRetrofit movieRetrofit;


    @Override
    public void read(String id, IServiceResultListener<String> resultListener) {
        Call<Movie> call = this.movieRetrofit.read(id);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                int statusCode = response.code();
                if(statusCode == 200) {
                    Movie movie = response.body();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getScreening(IServiceResultListener<String> resultListener){
        Call<List<Movie>> call = this.movieRetrofit.getSreening();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    List<Movie> movies = response.body();
                    //resultListener.onResult(movies);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public  void getUpcomming(IServiceResultListener<String> resultListener){
        Call<List<Movie>> call = this.movieRetrofit.getUpcomming();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    List<Movie> movies = response.body();
                    //resultListener.onResult(movies);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
