package com.cinematching.application.webservice;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cinematching.application.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public class AuthenticationInterceptor implements Interceptor {

    private String token;

    public AuthenticationInterceptor(String token) {
        this.token = String.format("%s %s", Constants.AUTH_TOKEN_PREFIX, token);
    }

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", token);

        Request request = builder.build();
        Log.d("Interceptor", String.format("Request: [%s]", request.toString()));
        return chain.proceed(request);
    }

    public String getCurrentToken() {
        return token;
    }
}
