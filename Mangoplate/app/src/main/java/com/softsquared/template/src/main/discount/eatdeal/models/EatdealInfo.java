package com.softsquared.template.src.main.discount.eatdeal.models;

import com.google.gson.annotations.SerializedName;

public class EatdealInfo {

    public int getEatdealId() {
        return eatdealId;
    }

    public int getAreaId() {
        return areaId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getPercent() {
        return percent;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getTitle() {
        return title;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantity() {
        return quantity;
    }

    @SerializedName("eatdealId")
    private int eatdealId;

    @SerializedName("areaId")
    private int areaId;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("status")
    private String status;

    @SerializedName("percent")
    private String percent;

    @SerializedName("originalPrice")
    private String originalPrice;

    @SerializedName("salePrice")
    private String salePrice;

    @SerializedName("title")
    private String title;

    @SerializedName("item")
    private String item;

    @SerializedName("description")
    private String description;

    @SerializedName("quantity")
    private String quantity;


}