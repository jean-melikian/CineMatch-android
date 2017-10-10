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

import java.io.IOException;
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
        Call<List<Movie>> call = getMovieRetrofit().getSreening();
        ServiceResult<List<Movie>> result = new ServiceResult<>();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    Log.d("Movies", "Success ! Code: " + response.code());

                    if (response.body() != null) {
                        Log.d("Movies", "Success ! List size: " + response.body().size());
                        result.setData(response.body());
                    } else {
                        try {
                            Log.e("Movies", String.format("Code=%d; Error: %s", response.code(), response.errorBody().string()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.d("Movies", "Error ! Code: " + response.code());
                }
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                t.printStackTrace();
                Log.d("Movies", "Exception !");
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }

    @Override
    public  void getUpcomming(IServiceResultListener<List<Movie>> resultListener) {
        Call<List<Movie>> call = getMovieRetrofit().getUpcomming();
        ServiceResult<List<Movie>> result = new ServiceResult<>();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {

                    List<Movie> usersList = new ArrayList<Movie>();

                    if (response.body() != null) {
                        Log.d("Movies", "Success ! List size: " + response.body().size());
                        List<Movie> apiResponse = response.body();
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
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }


}
