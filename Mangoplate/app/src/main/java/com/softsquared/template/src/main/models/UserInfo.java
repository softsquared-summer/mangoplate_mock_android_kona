package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;


    @SerializedName("profileUrl")
    private String profileUrl;

    @SerializedName("name")
    private String name;

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }



}