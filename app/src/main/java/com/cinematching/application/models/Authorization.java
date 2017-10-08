package com.cinematching.application.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jean-Christophe Melikian on 09/10/2017.
 */

public class Authorization {
    @SerializedName("access_token")
    private String token;
    @SerializedName("expires_in")
    private int tokenValidityInSeconds;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("scope")
    @Nullable
    private String scope;
    @SerializedName("refresh_token")
    private String refreshToken;

    public Authorization() {
    }

    public Authorization(String token, int tokenValidityInSeconds, String tokenType, String scope, String refreshToken) {
        this.token = token;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
        this.tokenType = tokenType;
        this.scope = scope;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Nullable
    public String getScope() {
        return scope;
    }

    public void setScope(@Nullable String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "token='" + token + '\'' +
                ", tokenValidityInSeconds=" + tokenValidityInSeconds +
                ", tokenType='" + tokenType + '\'' +
                ", scope='" + scope + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
