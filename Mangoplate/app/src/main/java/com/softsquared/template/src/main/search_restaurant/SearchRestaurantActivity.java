package com.softsquared.template.src.main.search_restaurant;

import android.os.Bundle;

import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;

import androidx.annotation.Nullable;

public class SearchRestaurantActivity extends BaseActivity implements SearchRestaurantActivityView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void GetSearchRestaurantOnSuccess(SearchRestaurantInfo searchRestaurantInfo) {

    }

    @Override
    public void GetSearchRestaurantOnFailure() {

    }


}
