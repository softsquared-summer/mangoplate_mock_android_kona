package com.softsquared.template.src.main.search_restaurant;

import android.content.Context;
import android.util.Log;

import com.softsquared.template.R;
import com.softsquared.template.src.ApplicationClass;
import com.softsquared.template.src.main.MainActivity;
import com.softsquared.template.src.main.discount.eatdeal.EatdealRecyclerAdapter;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailResponse;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantRetrofitInterface;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoResponse;

import org.jetbrains.annotations.NotNull;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;
import static com.softsquared.template.src.ApplicationClass.lat;
import static com.softsquared.template.src.ApplicationClass.lng;

public class SearchRestaurantService {
    private SearchRestaurantActivityView searchRestaurantActivityView;
    private MainActivity mainActivity;
    private SearchRestaurantRecyclerAdapter searchRestaurantRecyclerAdapter;
    private SearchRestaurantFragment searchRestaurantFragment;
    private Context context;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;


    public SearchRestaurantService(SearchRestaurantActivityView searchRestaurantActivityView)
    {
        this.searchRestaurantActivityView = searchRestaurantActivityView;
    }


void getTopPhoto() {
    SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
    searchRestaurantRetrofitInterface.getTopPhoto(X_ACCESS_TOKEN,"main").enqueue(new Callback<TopPhotoResponse>() {
        @Override
        public void onResponse(@NotNull Call<TopPhotoResponse> call, @NotNull Response<TopPhotoResponse> response) {
            TopPhotoResponse topPhotoResponse = response.body();
            if(topPhotoResponse == null) {
                Log.d("Topphoto", "SearchRestaurantService::getBannerAd() failure. topPhotoResponse is null");
                searchRestaurantActivityView.GetTopPhotoOnFailure();
                return;
            }
            else if(!topPhotoResponse.getIsSuccess()) {
                Log.d("Topphoto", "SearchRestaurantService::getBannerAd() failure. topPhotoResponse code: " + topPhotoResponse.getCode());
                Log.d("Topphoto", "SearchRestaurantService::getBannerAd() failure. topPhotoResponse message: " + topPhotoResponse.getMessage());
                searchRestaurantActivityView.GetTopPhotoOnFailure();
                return;
            }

            searchRestaurantActivityView.GetTopPhotoOnSuccess(topPhotoResponse.getResult());
        }

        @Override
        public void onFailure(@NotNull Call<TopPhotoResponse> call, @NotNull Throwable t) {
            Log.d("Topphoto", "SearchRestaurantService::getBannerAd() Failure: " + t);
            searchRestaurantActivityView.GetTopPhotoOnFailure();
        }
    });
}

    // API 4-1
    public void getRestaurantList() {
        SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.getSearchRestaurant(
                X_ACCESS_TOKEN, lat, lng, "main",
                "금천구"
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
//                    searchRestaurantActivityView.GetSearchRestaurantOnFailure();
                    return;
                }
                Log.d(TAG, "SearchRestaurantService::getRestaurantList() lat: " + lat + ", lng: " + lng);
                searchRestaurantActivityView.GetSearchRestaurantOnSuccess(searchRestaurantResponse);
            }

            @Override
            public void onFailure(@NotNull Call<SearchRestaurantResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure: " + t);
                searchRestaurantActivityView.GetSearchRestaurantOnFailure();
            }
        });
    }


}
