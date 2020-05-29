package com.softsquared.template.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailKeywordInfo {
    public String getKeyword() {
        return keyword;
    }

    @SerializedName("keyword")
    private String keyword;

}