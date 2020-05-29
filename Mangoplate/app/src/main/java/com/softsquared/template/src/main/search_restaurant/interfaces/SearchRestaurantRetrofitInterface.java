package com.softsquared.template.src.main.search_restaurant.interfaces;

import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRestaurantRetrofitInterface {


    @GET("/events")
    Call<TopPhotoResponse> getTopPhoto(
            @Query("type") String type
    );


    @GET("/restaurants")
    Call<SearchRestaurantResponse> getSearchRestaurant(
            @Query("lat") float lat,
            @Query("lng") float lng,
            @Query("type") String type,
            @Query("area") String area,
            @Query("order") String order,
            @Query("category") String category,
            @Query("kind") String kind,
            @Query("price") String price,
            @Query("parking") String parking,
            @Query("radius") String radius,
            @Query("page") String page,
            @Query("size") String size
    );

}
