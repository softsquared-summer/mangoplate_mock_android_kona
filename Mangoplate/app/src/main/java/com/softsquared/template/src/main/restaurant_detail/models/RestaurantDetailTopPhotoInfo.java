package com.softsquared.template.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailTopPhotoInfo {
    @SerializedName("imageId")
    private int imageId;

    @SerializedName("imageUrl")
    private String imageUrl;

    public int getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}