package com.cinematching.application.webservice.api.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean-Christophe Melikian on 09/10/2017.
 */

public class UsersListResponse {
    private List<UserResponse> usersResponse = new ArrayList<>();

    public UsersListResponse() {
    }

    public UsersListResponse(List<UserResponse> usersResponse) {
        this.usersResponse = usersResponse;
    }

    public List<UserResponse> getUsersResponse() {
        return usersResponse;
    }

    public void setUsersResponse(List<UserResponse> usersResponse) {
        this.usersResponse = usersResponse;
    }

    static class UserResponse {
        @SerializedName("id")
        int id;
        @SerializedName("name")
        String lastname;

        public UserResponse() {
        }

        public UserResponse(int id, String lastname) {
            this.id = id;
            this.lastname = lastname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    }
}
