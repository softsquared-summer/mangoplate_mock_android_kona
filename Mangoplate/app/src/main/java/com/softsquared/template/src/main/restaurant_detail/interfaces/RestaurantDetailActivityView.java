package com.softsquared.template.src.main.restaurant_detail.interfaces;


import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailInfo;

public interface RestaurantDetailActivityView {

    void GetRestaurantDetailOnSuccess(RestaurantDetailInfo restaurantDetailInfo);

    void GetRestaurantDetailOnFailure();
}
