package com.softsquared.template.src.main.search_restaurant.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class SearchRestaurantResponse {




    @SerializedName("result")
    private ArrayList<SearchRestaurantInfo> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public ArrayList<SearchRestaurantInfo> getResult() {
        return result;
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