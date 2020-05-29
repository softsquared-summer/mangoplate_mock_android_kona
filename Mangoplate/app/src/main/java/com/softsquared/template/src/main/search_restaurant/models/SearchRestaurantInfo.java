package com.softsquared.template.src.main.search_restaurant.models;

import com.google.gson.annotations.SerializedName;

public class SearchRestaurantInfo {


    public int getAreaId() {
        return areaId;
    }

    public String getArea() {
        return area;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getImg() {
        return img;
    }

    public String getStar() {
        return star;
    }

    public String getTitle() {
        return title;
    }

    public String getDistance() {
        return distance;
    }

    public String getSeenNum() {
        return seenNum;
    }

    public String getReviewNum() {
        return reviewNum;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public String getRating() {
        return rating;
    }

    @SerializedName("areaId")
    private int areaId;

    @SerializedName("area")
    private String area;

    @SerializedName("restaurantId")
    private int restaurantId;

    @SerializedName("lat")
    private float lat;

    @SerializedName("lng")
    private float lng;

    @SerializedName("img")
    private String img;

    @SerializedName("star")
    private String star;

    @SerializedName("title")
    private String title;

    @SerializedName("distance")
    private String distance;

    @SerializedName("seenNum")
    private String seenNum;

    @SerializedName("reviewNum")
    private String reviewNum;

    @SerializedName("ratingColor")
    private String ratingColor;

    @SerializedName("rating")
    private String rating;


}