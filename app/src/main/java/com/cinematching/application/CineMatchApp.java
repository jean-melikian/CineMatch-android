package com.cinematching.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.cinematching.application.webservice.ServiceGenerator;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

/**
 * Created by Jean-Christophe Melikian on 05/09/2017.
 */

public class CineMatchApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        validateAuthentication();
    }

    public boolean validateAuthentication() {
        String tokenExpirationTimeStr = getSharedPreferences("authorization", Context.MODE_PRIVATE).getString("expiration_time", null);

        if (tokenExpirationTimeStr != null) {
            DateTime expirationTime = DateTime.parse(tokenExpirationTimeStr);
            if (expirationTime.isBeforeNow()) {
                getSharedPreferences("authorization", Context.MODE_PRIVATE).edit().clear().commit();
            } else if (BuildConfig.DEBUG) {
                Seconds s = Seconds.secondsBetween(DateTime.now(), expirationTime);
                Log.d("TokenExpiration", String.format("%d seconds left until expiration of your token", s.getSeconds()));
            }
        }
        String authToken = getSharedPreferences("authorization", Context.MODE_PRIVATE).getString("auth_token", null);
        if (authToken != null && !authToken.isEmpty()) {
            ServiceGenerator.injectToken(authToken);
        }
        return ServiceGenerator.hasAuthorization();
    }
}
