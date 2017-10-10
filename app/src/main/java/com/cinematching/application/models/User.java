package com.cinematching.application.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.Years;

import java.util.Date;

/**
 * Created by meryl on 06/09/2017.
 */

public class User {

    private Integer id;
    @SerializedName("username")
    private String nickname;

    private String firstname;
    private String lastname;
    @Nullable
    private String city;
    @Nullable
    private String country;
    @Nullable
    private Date birthdate;
    @Nullable
    private String zipcode;
    private String email;
    @Exclude
    @Nullable
    private Integer note;
    @Nullable
    private String contact;
    @Exclude
    @Nullable
    private Integer age;

    public User() {
    }


    public User(Integer id, String nickname, String firstname, String lastname, @Nullable String city, @Nullable String country, @Nullable Date birthdate, @Nullable String zipcode, String email, @Nullable String contact) {
        this.id = id;
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.birthdate = birthdate;
        this.zipcode = zipcode;
        this.email = email;
        this.contact = contact;
        this.setAge();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Nullable
    public String getCity() {
        return city;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }

    @Nullable
    public String getCountry() {
        return country;
    }

    public void setCountry(@Nullable String country) {
        this.country = country;
    }

    @Nullable
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@Nullable Date birthdate) {
        this.birthdate = birthdate;
    }

    @Nullable
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(@Nullable String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public Integer getNote() {
        return note;
    }

    public void setNote(@Nullable Integer note) {
        this.note = note;
    }

    @Nullable
    public String getContact() {
        return contact;
    }

    public void setContact(@Nullable String contact) {
        this.contact = contact;
    }

    public Integer getAge() {
        return this.age;
    }

    private void setAge() {
        if (birthdate != null) {
            this.age = Years.yearsBetween(new DateTime(this.getBirthdate()), DateTime.now()).getYears();
        } else {
            this.age = 0;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", birthdate=" + birthdate +
                ", zipcode='" + zipcode + '\'' +
                ", email='" + email + '\'' +
                ", note=" + note +
                ", contact='" + contact + '\'' +
                ", age=" + age +
                '}';
    }
}
