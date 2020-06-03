package com.softsquared.template.src.main.search_restaurant;

import android.content.Context;
import android.util.Log;

import com.softsquared.template.src.ApplicationClass;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantRetrofitInterface;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class SearchRestaurantService {
    private final   SearchRestaurantActivityView searchRestaurantActivityView;

    private SearchRestaurantFragment searchRestaurantFragment;
    private Context context;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;
    SearchRestaurantRecyclerAdapter searchRestaurantRecyclerAdapter;


    public SearchRestaurantService(SearchRestaurantActivityView searchRestaurantActivityView) {
        this.searchRestaurantActivityView = searchRestaurantActivityView;
    }

/*  void getTopPhoto()
    {
        final SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface =getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.getTopPhoto("type").enqueue(new Callback<TopPhotoResponse>() {
            @Override
            public void onResponse(Call<TopPhotoResponse> call, Response<TopPhotoResponse> response) {
                TopPhotoResponse topPhotoResponse = response.body();
                if(topPhotoResponse == null)
                {
                    Log.e(TAG, "nullnullnull");
                    searchRestaurantActivityView.GetTopPhotoOnFailure();
                    return;
                }
                else if(topPhotoResponse != null)
                {
                    Log.e(TAG, "" + topPhotoResponse.getCode());
                    Log.e(TAG, "" + topPhotoResponse.getMessage());
                    searchRestaurantActivityView.GetTopPhotoOnSuccess(topPhotoResponse.getResult());
                }
            }

            @Override
            public void onFailure(Call<TopPhotoResponse> call, Throwable t) {
                searchRestaurantActivityView.GetTopPhotoOnFailure();

            }
        });
    }

    void getSearchRestaurant(float lat, float lng, String area, String order, String category,
                             String kind, String price, String parking, String radius, String page, String size)
    {
        final SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.getSearchRestaurant((float)37.455359, (float)126.891618, "main", "금천구", null, null, null,null
        ,null, null, null).enqueue(new Callback<SearchRestaurantResponse>() {
            @Override
            public void onResponse(Call<SearchRestaurantResponse> call, Response<SearchRestaurantResponse> response) {
                SearchRestaurantResponse searchRestaurantResponse = response.body();
                if(searchRestaurantResponse == null)
                {
                    Log.e(TAG, "UserResponse is null");
                    searchRestaurantActivityView.GetSearchRestaurantOnFailure();
                    return;
                }
                else if(!searchRestaurantResponse.getIsSuccess())
                {
                    Log.e(TAG, "" + searchRestaurantResponse.getCode());
                    Log.e(TAG, "" + searchRestaurantResponse.getMessage());


                }
                searchRestaurantFragment.GetSearchRestaurantOnSuccess(searchRestaurantResponse);
            }

            @Override
            public void onFailure(Call<SearchRestaurantResponse> call, Throwable t) {
                searchRestaurantActivityView.GetSearchRestaurantOnFailure();

            }
        });
    }
    */
void getTopPhoto() {
    SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
    searchRestaurantRetrofitInterface.getTopPhoto("main").enqueue(new Callback<TopPhotoResponse>() {
        @Override
        public void onResponse(@NotNull Call<TopPhotoResponse> call, @NotNull Response<TopPhotoResponse> response) {
            TopPhotoResponse topPhotoResponse = response.body();
            if(topPhotoResponse == null) {
                Log.d(TAG, "SearchRestaurantService::getBannerAd() failure. topPhotoResponse is null");
                searchRestaurantActivityView.GetTopPhotoOnFailure();
                return;
            }
            else if(!topPhotoResponse.getIsSuccess()) {
                Log.d(TAG, "SearchRestaurantService::getBannerAd() failure. topPhotoResponse code: " + topPhotoResponse.getCode());
                Log.d(TAG, "SearchRestaurantService::getBannerAd() failure. topPhotoResponse message: " + topPhotoResponse.getMessage());
                searchRestaurantActivityView.GetTopPhotoOnFailure();
                return;
            }

            searchRestaurantActivityView.GetTopPhotoOnSuccess(topPhotoResponse.getResult());
        }

        @Override
        public void onFailure(@NotNull Call<TopPhotoResponse> call, @NotNull Throwable t) {
            Log.d(TAG, "SearchRestaurantService::getBannerAd() Failure: " + t);
            searchRestaurantActivityView.GetTopPhotoOnFailure();
        }
    });
}

    // API 4-1
    public void getRestaurantList(float lat, float lng,
                                  String area, String order, String category,
                                  String kind, String price, String parking,
                                  String radius, String page, String size) {
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() lat: " + lat + ", lng: " + lng);
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() area: " + area + ", order: " + order + ", category: " + category);
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() kind: " + kind + ", price: " + price + ", parking: " + parking);
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() radius: " + radius + ", page: " + page + ", size: " + size);

        SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.getSearchRestaurant(
                lat, lng, "main",
                area, order, category,
                price, parking,
                radius, page, size
        ).enqueue(new Callback<SearchRestaurantResponse>() {
            @Override
            public void onResponse(@NotNull Call<SearchRestaurantResponse> call, @NotNull Response<SearchRestaurantResponse> response) {
                SearchRestaurantResponse searchRestaurantResponse = response.body();
                if(searchRestaurantResponse == null) {
                    Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure. restaurantListResponse is null");
                    searchRestaurantActivityView.GetSearchRestaurantOnFailure();
                    return;
                }
                else if(!searchRestaurantResponse.getIsSuccess()) {
                    Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure. restaurantListResponse code: " + searchRestaurantResponse.getCode());
                    Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure. restaurantListResponse message: " + searchRestaurantResponse.getMessage());
                    searchRestaurantActivityView.GetSearchRestaurantOnFailure();
                    return;
                }

                searchRestaurantActivityView.GetSearchRestaurantOnSuccess(searchRestaurantResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<SearchRestaurantResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure: " + t);
                searchRestaurantActivityView.GetSearchRestaurantOnFailure();
            }
        });
    }

}
