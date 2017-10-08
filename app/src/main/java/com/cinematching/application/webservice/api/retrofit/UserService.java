package com.cinematching.application.webservice.api.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cinematching.application.models.Authorization;
import com.cinematching.application.models.Credentials;
import com.cinematching.application.models.User;
import com.cinematching.application.webservice.IServiceResultListener;
import com.cinematching.application.webservice.ServiceGenerator;
import com.cinematching.application.webservice.ServiceResult;
import com.cinematching.application.webservice.api.IUserService;
import com.cinematching.application.webservice.errors.ServiceException;
import com.cinematching.application.webservice.errors.ServiceExceptionType;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public class UserService implements IUserService {

    private UserRetrofit userRetrofit;

    private UserRetrofit getUserRetrofit() {
        // TODO: check if token is available
        if (userRetrofit == null) {
            userRetrofit = ServiceGenerator.createService(UserRetrofit.class, null);
        }
        return userRetrofit;
    }

    @Override
    public void authenticate(Credentials credentials, IServiceResultListener<Authorization> resultListener) {
        Call<Authorization> call = getUserRetrofit().authenticate(credentials);
        Log.d("Authenticate", "Call: " + call.request().body());

        ServiceResult<Authorization> result = new ServiceResult<>();

        call.enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(@NonNull Call<Authorization> call, @NonNull Response<Authorization> response) {
                if (response.isSuccessful()) {
                    result.setData(response.body());
                    Log.d("Authenticate", "Success ! Authorization: " + result.getData().toString());
                } else {
                    Log.e("Authenticate", "Error code: " + String.valueOf(response.code()) + "; Error body: " + response.message());
                    if (response.code() == 400) {
                        result.setError(new ServiceException(ServiceExceptionType.BAD_REQUEST));
                    }
                }

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Authorization> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                Log.e("Authenticate", "onFailure: " + t.getMessage());
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });


    }

    @Override
    public void create(User user, IServiceResultListener<String> resultListener) {
        Call<String> call = getUserRetrofit().create(user);
        ServiceResult<String> result = new ServiceResult<String>();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 201) {
                        result.setData(response.body());
                    }
                }
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }

    @Override
    public void read(IServiceResultListener<List<User>> resultListener) {
        Call<UsersListResponse> call = getUserRetrofit().read();
        ServiceResult<List<User>> result = new ServiceResult<>();

        call.enqueue(new Callback<UsersListResponse>() {
            @Override
            public void onResponse(@NonNull Call<UsersListResponse> call, @NonNull Response<UsersListResponse> response) {
                if (response.isSuccessful()) {
                    List<User> usersList = new ArrayList<User>();
                    if (response.body() != null) {
                        for (UsersListResponse.UserResponse entry : response.body().getUsersResponse()) {
                            readById(String.valueOf(entry.getId()), new IServiceResultListener<User>() {
                                @Override
                                public void onResult(ServiceResult<User> result) {
                                    if (result.getData() != null) {
                                        usersList.add(result.getData());
                                    }
                                }
                            });
                        }
                    }
                    result.setData(usersList);
                }
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UsersListResponse> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });

    }

    @Override
    public void readById(String id, IServiceResultListener<User> resultListener) {
        Call<User> call = getUserRetrofit().readById(id);

        ServiceResult<User> result = new ServiceResult<>();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    result.setData(response.body());
                }
                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });

    }
}
