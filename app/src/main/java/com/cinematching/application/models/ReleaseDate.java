package com.cinematching.application.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jean-Christophe Melikian on 10/10/2017.
 */

public class ReleaseDate {
    @SerializedName("date")
    String date;
    @SerializedName("timezone_type")
    int timezoneType;
    @SerializedName("timezone")
    String timezone;

    public ReleaseDate() {
    }

    public ReleaseDate(String date, int timezoneType, String timezone) {
        this.date = date;
        this.timezoneType = timezoneType;
        this.timezone = timezone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimezoneType() {
        return timezoneType;
    }

    public void setTimezoneType(int timezoneType) {
        this.timezoneType = timezoneType;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "ReleaseDate{" +
                "date='" + date + '\'' +
                ", timezoneType=" + timezoneType +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}