package com.softsquared.template.src.main.search_restaurant.interfaces;


import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailInfo;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;

public interface SearchRestaurantActivityView {

    void GetSearchRestaurantOnSuccess(SearchRestaurantInfo searchRestaurantInfo);

    void GetSearchRestaurantOnFailure();
}
