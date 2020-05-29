package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.login.models.DefaultResponse;
import com.softsquared.template.src.main.models.UserInfoResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
//    @GET("/test")

    @GET("/user")
    Call<UserInfoResponse> getUserInfo();
}
