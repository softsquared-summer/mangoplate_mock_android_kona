package com.softsquared.template.src.main.discount.eatdeal.interfaces;

import com.softsquared.template.src.main.discount.eatdeal.models.EatdealResponse;
import com.softsquared.template.src.main.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EatdealRetrofitInterface {
//    @GET("/test")

    @GET("/eatdeals")
    @Headers("Content-Type: application/json")
    Call<EatdealResponse> getEatdeal(
            @Header("x-access-token") String accessToken
    );
}
