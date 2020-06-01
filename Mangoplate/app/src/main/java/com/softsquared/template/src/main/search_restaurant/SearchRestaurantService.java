package com.softsquared.template.src.main.search_restaurant;

import android.content.Context;
import android.util.Log;

import com.softsquared.template.src.ApplicationClass;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantRetrofitInterface;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class SearchRestaurantService {
    private SearchRestaurantActivityView searchRestaurantActivityView;

    private SearchRestaurantFragment searchRestaurantFragment;
    private Context context;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;
    SearchRestaurantRecyclerAdapter searchRestaurantRecyclerAdapter;


    public SearchRestaurantService(SearchRestaurantFragment searchRestaurantFragment) {
        this.searchRestaurantFragment = searchRestaurantFragment;
    }

  void getTopPhoto()
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

    void getSearchRestaurant(/*float lat, float lng, String area, String order, String category,
                             String kind, String price, String parking, String radius, String page, String size*/)
    {
        final SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.getSearchRestaurant(X_ACCESS_TOKEN, ApplicationClass.lat, ApplicationClass.lng, "main", "금천구").enqueue(new Callback<SearchRestaurantResponse>() {
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
}
