package com.softsquared.template.src.login.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    public LoginInfo getResult() {
        return result;
    }

    @SerializedName("result")
    private LoginInfo result;


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

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