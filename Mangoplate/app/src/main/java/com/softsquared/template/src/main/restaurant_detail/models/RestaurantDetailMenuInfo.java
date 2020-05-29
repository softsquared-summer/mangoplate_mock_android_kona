package com.softsquared.template.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailMenuInfo {
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;


}