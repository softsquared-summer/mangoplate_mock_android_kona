package com.softsquared.template.src.main.discount.eatdeal.interfaces;

import com.softsquared.template.src.main.discount.eatdeal.models.EatdealResponse;
import com.softsquared.template.src.main.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EatdealRetrofitInterface {
//    @GET("/test")

    @GET("/eatdeals")
    Call<EatdealResponse> getUserInfo(
            @Query("area") String area
    );
}
