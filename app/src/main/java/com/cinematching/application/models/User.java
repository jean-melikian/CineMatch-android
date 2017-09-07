package com.cinematching.application.models;

import java.util.Date;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public class User {

    private long id;

    private String nickname;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private Date birthdate;

    public User() {
    }

    public User(long id, String nickname, String firstname, String lastname, String email, String password) {
        this.id = id;
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
