package com.softsquared.template.src.main.search_restaurant;

import android.content.Context;

import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class SearchRestaurantService {

    private SearchRestaurantFragment searchRestaurantFragment;
    private Context context;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;
    SearchRestaurantRecyclerAdapter searchRestaurantRecyclerAdapter;


    public SearchRestaurantService(SearchRestaurantFragment searchRestaurantFragment, Context context) {
        this.context = context;
        this.searchRestaurantFragment = searchRestaurantFragment;
    }

    void getSearchRestaurant()
    {
        final SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
//        searchRestaurantRetrofitInterface.getSearchRestaurant().enqueue();
    }
}
