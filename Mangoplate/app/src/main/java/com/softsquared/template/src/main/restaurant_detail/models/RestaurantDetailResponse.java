package com.softsquared.template.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;


public class RestaurantDetailResponse {
    public RestaurantDetailInfo getResult() {
        return result;
    }

    @SerializedName("result")
    private RestaurantDetailInfo result;

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