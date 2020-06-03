package com.softsquared.template.src.main.search_restaurant.interfaces;

import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchRestaurantRetrofitInterface {


    @GET("/events")
    @Headers("Content-Type: application/json")
    Call<TopPhotoResponse> getTopPhoto(
            @Header("x-access-token") String accessToken,
            @Query("type") String type
    );


    @GET("/restaurants")
    @Headers("Content-Type: application/json")
    Call<SearchRestaurantResponse> getSearchRestaurant(
            @Header("x-access-token") String accessToken,
            @Query("lat") float lat,
            @Query("lng") float lng,
            @Query("type") String type,
            @Query("area") String area

    );

}
