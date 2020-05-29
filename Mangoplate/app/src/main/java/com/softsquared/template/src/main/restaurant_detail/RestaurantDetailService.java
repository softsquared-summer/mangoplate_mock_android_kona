package com.softsquared.template.src.main.restaurant_detail;

import android.content.Context;
import android.util.Log;

import com.softsquared.template.src.main.restaurant_detail.interfaces.RestaurantDetailActivityView;
import com.softsquared.template.src.main.restaurant_detail.interfaces.RestaurantDetailRetrofitInterface;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

class RestaurantDetailService {

    private final RestaurantDetailActivityView restaurantDetailActivityView;
    private final Context context;

    RestaurantDetailService(RestaurantDetailActivityView restaurantDetailActivityView, Context context) {
        this.restaurantDetailActivityView =restaurantDetailActivityView;
        this.context = context;
    }

    void getRestaurantDetail(int restaurantId)
    {
        final RestaurantDetailRetrofitInterface restaurantDetailRetrofitInterface = getRetrofit().create(RestaurantDetailRetrofitInterface.class);
        restaurantDetailRetrofitInterface.getRestaurantDetail().enqueue(new Callback<RestaurantDetailResponse>() {
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
