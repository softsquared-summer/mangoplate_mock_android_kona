package com.softsquared.template.src.main.discount.eatdeal.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;

import java.util.ArrayList;


public class EatdealResponse {




    @SerializedName("result")
    private ArrayList<EatdealInfo> result = null;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public ArrayList<EatdealInfo> getResult() {
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