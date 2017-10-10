package com.cinematching.application.webservice;

import android.support.annotation.Nullable;
import android.util.Log;

import com.cinematching.application.BuildConfig;
import com.cinematching.application.Constants;
import com.cinematching.application.models.Exclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

    private static AuthenticationInterceptor authInterceptor = null;
    private static HttpLoggingInterceptor logging = null;

    private static String token;

    private static boolean hasAuthorization;
    private static boolean shouldInjectToken = false;


    public static Retrofit get() {

        if (token != null) {
            if (shouldInjectToken) {
                if (authInterceptor == null) {
                    authInterceptor = new AuthenticationInterceptor(token);
                }

                if (!httpClientBuilder.interceptors().contains(authInterceptor)) {
                    httpClientBuilder.interceptors().add(authInterceptor);
                    if (BuildConfig.DEBUG) {
                        Log.d("WebService", String.format("Added authInterceptor with token: %s", token));
                    }
                    hasAuthorization = true;
                }
            }
        } else {
            hasAuthorization = false;
            Log.d("WebService", "Not authenticated, no token available...");
        }

        if (instance == null || shouldInjectToken) {

            retrofitBuilder
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(getGson()));

            if (BuildConfig.DEBUG) {
                if (logging == null) {
                    logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                }

                if (!httpClientBuilder.interceptors().contains(logging)) {
                    httpClientBuilder.addInterceptor(logging);
                }
            }

            httpClient = httpClientBuilder.build();

            instance = retrofitBuilder
                    .client(httpClient)
                    .build();
        }
        shouldInjectToken = false;
        return instance;
    }

    public static void injectToken(String token) {
        ServiceGenerator.token = token;
        shouldInjectToken = true;
        get();
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
            builder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
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

    public static boolean hasAuthorization() {
        return hasAuthorization;
    }
}
