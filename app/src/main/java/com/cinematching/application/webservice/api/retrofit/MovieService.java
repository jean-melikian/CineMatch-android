package com.cinematching.application.webservice.api.retrofit;


import android.support.annotation.NonNull;

import com.cinematching.application.controllers.fragments.main.MovieListFragment;
import com.cinematching.application.models.Movie;
import com.cinematching.application.webservice.IServiceResultListener;
import com.cinematching.application.webservice.ServiceGenerator;
import com.cinematching.application.webservice.ServiceResult;
import com.cinematching.application.webservice.api.IMovieService;
import com.cinematching.application.webservice.errors.ServiceException;
import com.cinematching.application.webservice.errors.ServiceExceptionType;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by meryl on 08/10/2017.
 */

public class MovieService implements IMovieService {

    private MovieRetrofit movieRetrofit;

    private MovieRetrofit getMovieRetrofit() {
        // TODO: check if token is available
        if (movieRetrofit == null) {
            movieRetrofit = ServiceGenerator.createService(MovieRetrofit.class, null);
        }
        return movieRetrofit;
    }

    @Override
    public void read(String id, IServiceResultListener<Movie> resultListener) {
        Call<Movie> call = getMovieRetrofit().read(id);

        ServiceResult<Movie> result = new ServiceResult<>();

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()) {
                    result.setData(response.body());
                }
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }

    @Override
    public void getScreening(IServiceResultListener<List<Movie>> resultListener){
        Call<MoviesListResponse> call = getMovieRetrofit().getSreening();
        ServiceResult<List<Movie>> result = new ServiceResult<>();

        call.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesListResponse> call, @NonNull Response<MoviesListResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movieList = new ArrayList<Movie>();
                    if (response.body() != null) {
                        for (MoviesListResponse.MoviesResponse entry : response.body().getMoviesResponse()) {
                            read(String.valueOf(entry.getId()), new IServiceResultListener<Movie>() {
                                @Override
                                public void onResult(ServiceResult<Movie> result) {
                                    if (result.getData() != null) {
                                        movieList.add(result.getData());
                                    }
                                }
                            });
                        }
                    }
                    result.setData(movieList);
                }
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesListResponse> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });

    }

    @Override
    public  void getUpcomming(IServiceResultListener<List<Movie>> resultListener){
        Call<MoviesListResponse> call = getMovieRetrofit().getUpcomming();
        ServiceResult<List<Movie>> result = new ServiceResult<>();

        call.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesListResponse> call, @NonNull Response<MoviesListResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movieList = new ArrayList<Movie>();
                    if (response.body() != null) {
                        for (MoviesListResponse.MoviesResponse entry : response.body().getMoviesResponse()) {
                            read(String.valueOf(entry.getId()), new IServiceResultListener<Movie>() {
                                @Override
                                public void onResult(ServiceResult<Movie> result) {
                                    if (result.getData() != null) {
                                        movieList.add(result.getData());
                                    }
                                }
                            });
                        }
                    }
                    result.setData(movieList);
                }
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesListResponse> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }


}
