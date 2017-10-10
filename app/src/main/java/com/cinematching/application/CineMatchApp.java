package com.cinematching.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.cinematching.application.controllers.LoginActivity;
import com.cinematching.application.controllers.MainActivity;
import com.cinematching.application.webservice.ServiceGenerator;

/**
 * Created by Jean-Christophe Melikian on 05/09/2017.
 */

public class CineMatchApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (validateAuthentication()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public boolean validateAuthentication() {
        String authToken = getSharedPreferences("authorization", Context.MODE_PRIVATE).getString("authToken", null);
        if (authToken != null && !authToken.isEmpty()) {
            ServiceGenerator.injectToken(authToken);
        }
        return ServiceGenerator.hasAuthorization();
    }
}
