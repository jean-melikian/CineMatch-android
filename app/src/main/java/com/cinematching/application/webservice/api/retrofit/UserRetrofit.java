package com.cinematching.application.webservice.api.retrofit;

import com.cinematching.application.models.Authorization;
import com.cinematching.application.models.Credentials;
import com.cinematching.application.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public interface UserRetrofit {

    @POST("oauth/v2/token")
    Call<Authorization> authenticate(@Body Credentials credentials);

    @POST("api/users")
    Call<String> create(@Body User user);

    @GET("api/users")
    Call<UsersListResponse> read();

    @GET("api/users/{id}")
    Call<User> readById(@Path(value = "id", encoded = true) String id);
}
