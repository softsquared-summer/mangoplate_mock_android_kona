package com.softsquared.template.src.main.models;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {


    @SerializedName("result")
    private UserInfo userInfoResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public UserInfo getUserInfoResult() {
        return userInfoResult;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}