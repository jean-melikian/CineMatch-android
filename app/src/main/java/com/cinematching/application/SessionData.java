package com.cinematching.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cinematching.application.models.User;
import com.cinematching.application.webservice.ServiceGenerator;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

/**
 * Created by Jean-Christophe Melikian on 10/10/2017.
 */

public class SessionData {
    private static final SessionData INSTANCE = new SessionData();
    private Context context;
    private String token;
    private User currentUser;
    private DateTime expiration;
    private SharedPreferences sp;

    public static SessionData get() {
        return INSTANCE;
    }

    public void setContext(Context context) {
        this.context = context;
        sp = this.context.getSharedPreferences(Constants.SP_SESSION, Context.MODE_PRIVATE);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        sp.edit().putString("auth_token", token).apply();
        this.token = token;
    }

    public boolean validateAuthentication() {
        String tokenExpirationTimeStr = sp.getString("expiration_time", null);

        if (tokenExpirationTimeStr != null) {
            DateTime expirationTime = DateTime.parse(tokenExpirationTimeStr);
            if (expirationTime.isBeforeNow()) {
                clearSession();
            } else if (BuildConfig.DEBUG) {
                Seconds s = Seconds.secondsBetween(DateTime.now(), expirationTime);
                Log.d("TokenExpiration", String.format("%d seconds left until expiration of your token", s.getSeconds()));
            }
        }
        String authToken = sp.getString("auth_token", null);
        if (authToken != null && !authToken.isEmpty()) {
            ServiceGenerator.injectToken(authToken);
        }
        return ServiceGenerator.hasAuthorization();
    }

    public void clearSession() {
        sp.edit().clear().apply();
    }

    public DateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(DateTime expiration) {
        this.expiration = expiration;
        sp.edit().putString("expiration_time", this.expiration.toString()).apply();
    }
}
