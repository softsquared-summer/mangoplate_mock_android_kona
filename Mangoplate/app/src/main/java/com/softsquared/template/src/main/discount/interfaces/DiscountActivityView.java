package com.softsquared.template.src.main.discount.interfaces;


import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;

public interface DiscountActivityView {

    void GetSearchRestaurantOnSuccess(SearchRestaurantInfo searchRestaurantInfo);

    void GetSearchRestaurantOnFailure();
}
