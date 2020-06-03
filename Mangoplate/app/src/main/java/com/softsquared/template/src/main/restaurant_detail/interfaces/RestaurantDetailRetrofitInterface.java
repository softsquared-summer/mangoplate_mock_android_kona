package com.softsquared.template.src.main.restaurant_detail.interfaces;

import com.softsquared.template.src.login.models.DefaultResponse;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantDetailRetrofitInterface {

    @GET("/restaurants/{restaurantId}")
    @Headers("Content-Type: application/json")
    Call<RestaurantDetailResponse> getRestaurantDetail(
            @Header("x-access-token") String accessToken,
            @Path("restaurantId") int resId
    );

}
