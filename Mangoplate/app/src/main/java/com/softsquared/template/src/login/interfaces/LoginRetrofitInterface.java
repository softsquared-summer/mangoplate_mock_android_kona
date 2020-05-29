package com.softsquared.template.src.login.interfaces;

import com.softsquared.template.src.login.models.DefaultResponse;
import com.softsquared.template.src.login.models.LoginBody;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {
//    @GET("/test")
//    @POST("/jwt")
//    Call<DefaultResponse> getJwt();

//    @POST("/jwt")
//    Call<KakaoLoginResponse> getKakakoLogin(
//            @Query("type") String type
//    );

    @POST("/jwt")
    @Headers("Content-Type: application/json")
    Call<DefaultResponse> postFacebook(
            @Query("type") String type,
            @Body LoginBody loginbdody);

}
