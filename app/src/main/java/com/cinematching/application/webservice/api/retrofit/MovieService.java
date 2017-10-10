package com.cinematching.application.webservice.api.retrofit;


import android.support.annotation.NonNull;
import android.util.Log;

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
        Call<List<MovieApiResponse>> call = getMovieRetrofit().getSreening();
        ServiceResult<List<Movie>> result = new ServiceResult<>();

        call.enqueue(new Callback<List<MovieApiResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<MovieApiResponse>> call, @NonNull Response<List<MovieApiResponse>> response) {
                if (response.isSuccessful()) {

                    List<Movie> usersList = new ArrayList<Movie>();

                    if (response.body() != null) {
                        Log.d("Movies", "Success ! List size: " + response.body().size());
                        List<MovieApiResponse> apiResponse = response.body();
                        if (apiResponse != null) {
                            for (int i = 0; i < apiResponse.size(); i++) {
                                int finalI = i;
                                read(String.valueOf(apiResponse.get(i)), new IServiceResultListener<Movie>() {
                                    @Override
                                    public void onResult(ServiceResult<Movie> innerResult) {
                                        if (innerResult.getData() != null) {
                                            usersList.add(innerResult.getData());
                                        }
                                        if (finalI == apiResponse.size() - 1) {
                                            if (resultListener != null) {
                                                result.setData(usersList);
                                                resultListener.onResult(result);
                                            }
                                        }
                                    }
                                });

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MovieApiResponse>> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }

    @Override
    public  void getUpcomming(IServiceResultListener<List<Movie>> resultListener) {
        Call<List<MovieApiResponse>> call = getMovieRetrofit().getUpcomming();
        ServiceResult<List<Movie>> result = new ServiceResult<>();

        call.enqueue(new Callback<List<MovieApiResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<MovieApiResponse>> call, @NonNull Response<List<MovieApiResponse>> response) {
                if (response.isSuccessful()) {

                    List<Movie> usersList = new ArrayList<Movie>();

                    if (response.body() != null) {
                        Log.d("Movies", "Success ! List size: " + response.body().size());
                        List<MovieApiResponse> apiResponse = response.body();
                        if (apiResponse != null) {
                            for (int i = 0; i < apiResponse.size(); i++) {
                                int finalI = i;
                                read(String.valueOf(apiResponse.get(i)), new IServiceResultListener<Movie>() {
                                    @Override
                                    public void onResult(ServiceResult<Movie> innerResult) {
                                        if (innerResult.getData() != null) {
                                            usersList.add(innerResult.getData());
                                        }
                                        if (finalI == apiResponse.size() - 1) {
                                            if (resultListener != null) {
                                                result.setData(usersList);
                                                resultListener.onResult(result);
                                            }
                                        }
                                    }
                                });

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MovieApiResponse>> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }


}
