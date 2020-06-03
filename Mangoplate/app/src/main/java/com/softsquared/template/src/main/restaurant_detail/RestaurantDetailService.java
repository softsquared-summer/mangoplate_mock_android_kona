package com.softsquared.template.src.main.restaurant_detail;

import android.content.Context;
import android.util.Log;

import com.softsquared.template.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.template.src.main.models.UserInfoResponse;
import com.softsquared.template.src.main.restaurant_detail.interfaces.RestaurantDetailActivityView;
import com.softsquared.template.src.main.restaurant_detail.interfaces.RestaurantDetailRetrofitInterface;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailInfo;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

class RestaurantDetailService {

    private final RestaurantDetailActivityView restaurantDetailActivityView;
    int restaurantId;


    RestaurantDetailService(RestaurantDetailActivityView restaurantDetailActivityView, int restaurantId) {
        this.restaurantDetailActivityView =restaurantDetailActivityView;
        this.restaurantId = restaurantId;
    }

    void getRestaurantDetail()
    {
        final RestaurantDetailRetrofitInterface restaurantDetailRetrofitInterface = getRetrofit().create(RestaurantDetailRetrofitInterface.class);
        restaurantDetailRetrofitInterface.getRestaurantDetail(X_ACCESS_TOKEN, restaurantId).enqueue(new Callback<RestaurantDetailResponse>() {
            @Override
            public void onResponse(Call<RestaurantDetailResponse> call, Response<RestaurantDetailResponse> response) {
                RestaurantDetailResponse restaurantDetailResponse = response.body();
                if (restaurantDetailResponse == null)
                {
                    Log.d(TAG, "Restaurant Detail Response is null");
                    restaurantDetailActivityView.GetRestaurantDetailOnFailure();
                    return;
                }
                else if(!restaurantDetailResponse.getIsSuccess())
                {
                    Log.e("RES", ""+ restaurantId);
                    Log.d(TAG,"code : " + restaurantDetailResponse.getCode());
                    Log.d(TAG,"message : " + restaurantDetailResponse.getMessage());
                    restaurantDetailActivityView.GetRestaurantDetailOnFailure();
                    return;
                }
                restaurantDetailActivityView.GetRestaurantDetailOnSuccess(restaurantDetailResponse.getResult());

            }

            @Override
            public void onFailure(Call<RestaurantDetailResponse> call, Throwable t) {
                Log.d(TAG, "Response Fail");
                restaurantDetailActivityView.GetRestaurantDetailOnFailure();

            }
        });

    }
}
