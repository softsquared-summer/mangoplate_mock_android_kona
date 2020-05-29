package com.softsquared.template.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    public String getJwt() {
        return jwt;
    }

//    public int getUserId() {
//        return userId;
//    }

    @SerializedName("jwt")
    private String jwt;


//    @SerializedName("userId")
//    private int userId;
}
