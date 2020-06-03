package com.softsquared.template.src.main.search_restaurant.interfaces;


import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailInfo;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoInfo;

import java.util.ArrayList;

public interface SearchRestaurantActivityView {

    void GetTopPhotoOnSuccess(ArrayList<TopPhotoInfo> topPhotoInfoArrayList);

    void GetTopPhotoOnFailure();


    void GetSearchRestaurantOnSuccess(ArrayList<SearchRestaurantInfo> restaurantInfos);

    void GetSearchRestaurantOnFailure();
}
