package com.softsquared.template.src.main.announcement.interfaces;

import com.softsquared.template.src.main.announcement.models.AnnouncementResponse;
import com.softsquared.template.src.main.models.UserInfoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnnouncementRetrofitInterface {


    @GET("/reviews")
    Call<AnnouncementResponse> getAnnouncementInfo(
            @Query("type") String type

            );
}
