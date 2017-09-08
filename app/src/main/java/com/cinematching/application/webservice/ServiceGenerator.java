package com.cinematching.application.webservice;

import android.support.annotation.Nullable;
import android.util.Log;

import com.cinematching.application.Constants;
import com.cinematching.application.models.Exclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public class ServiceGenerator {

    private static Retrofit instance = null;

    private static Gson gson = null;

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

    private static OkHttpClient httpClient = new OkHttpClient();

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    private static AuthenticationInterceptor interceptor = null;

    private static String token;

    private static boolean hasAuthorization;


    public static Retrofit get() {

        if (token != null) {
            if (interceptor == null) {
                interceptor = new AuthenticationInterceptor(token);
            }

            if (!httpClientBuilder.interceptors().contains(interceptor)) {
                httpClientBuilder.interceptors().add(interceptor);
                Log.d("StimshopWebService", String.format("Added interceptor with token: %s", token));
                hasAuthorization = true;
            }
        } else {
            hasAuthorization = false;
            Log.e("StimshopWebService", "Not authenticated, no token available...");
        }

        if (instance == null) {

            retrofitBuilder
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(getGson()));

            httpClient = httpClientBuilder.build();

            instance = retrofitBuilder
                    .client(httpClient)
                    .build();
        }
        return instance;
    }


    /**
     * With this, you don't have to pass the auth token to every API call you make
     *
     * @param serviceClass The "IRF" interface service class
     * @param token        The auth token
     * @param <S>          The service class
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, @Nullable final String token) {
        if (token != null) {
            ServiceGenerator.token = token;
        }
        return get().create(serviceClass);
    }

    private static Gson getGson() {
        if (gson == null) {
            GsonBuilder builder = getDefaultGsonBuilder();
            gson = builder.create();
        }
        return gson;
    }

    private static GsonBuilder getDefaultGsonBuilder() {
        GsonBuilder defaultGsonBuilder = new GsonBuilder();
        defaultGsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(Exclude.class) != null &&
                        f.getAnnotation(Exclude.class).serialize();
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        defaultGsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(Exclude.class) != null &&
                        f.getAnnotation(Exclude.class).deserialize();
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        defaultGsonBuilder.setDateFormat("yyyy-MM-dd");
        return defaultGsonBuilder;
    }
}
