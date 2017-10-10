package com.cinematching.application.models;

import com.cinematching.application.Constants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jean-Christophe Melikian on 09/10/2017.
 */

public class Credentials {
    @SerializedName("grant_type")
    private static final String grantType = "password";
    @SerializedName("client_id")
    private static final String clientId = Constants.OAUTH_CLIENT_ID;
    @SerializedName("client_secret")
    private static final String clientSecret = Constants.OAUTH_CLIENT_SECRET;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public static String getGrantType() {
        return grantType;
    }

    public static String getClientId() {
        return clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validateInput() {
        return username != null && !username.isEmpty()
                && password != null && !password.isEmpty();
    }
}
