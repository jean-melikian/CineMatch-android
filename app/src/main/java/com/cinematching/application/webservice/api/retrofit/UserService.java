package com.cinematching.application.webservice.api.retrofit;

import android.support.annotation.NonNull;

import com.cinematching.application.models.User;
import com.cinematching.application.webservice.IServiceResultListener;
import com.cinematching.application.webservice.ServiceGenerator;
import com.cinematching.application.webservice.ServiceResult;
import com.cinematching.application.webservice.api.IUserService;
import com.cinematching.application.webservice.errors.ServiceException;
import com.cinematching.application.webservice.errors.ServiceExceptionType;

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
    public void read(String id, IServiceResultListener<String> resultListener) {

    }
}
