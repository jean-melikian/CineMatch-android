package com.cinematching.application.webservice.api.retrofit;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jean-Christophe Melikian on 09/10/2017.
 */

public class UserApiResponse {

    @SerializedName("id")
    int id;
    @Nullable
    @SerializedName("name")
    String lastname;

    public UserApiResponse() {
    }

    public UserApiResponse(int id, @Nullable String lastname) {
        this.id = id;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getLastname() {
        return lastname;
    }

    public void setLastname(@Nullable String lastname) {
        this.lastname = lastname;
    }
}
