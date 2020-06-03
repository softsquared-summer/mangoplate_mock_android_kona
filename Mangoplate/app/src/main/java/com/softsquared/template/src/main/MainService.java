package com.softsquared.template.src.main;

import android.util.Log;

import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.template.src.main.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class MainService {

    private final MainActivityView mainActivityView;


    public MainService(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    void getMyInfo()
    {
        MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getUserInfo(X_ACCESS_TOKEN).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                UserInfoResponse userInfoResponse = response.body();
                if(userInfoResponse == null)
                {
                    Log.d(TAG, "UserResponse is null");
                    mainActivityView.onFailureGetUserInfo();
                    return;
                }
                else if(!userInfoResponse.getIsSuccess())
                {
                    mainActivityView.onFailureGetUserInfo();
                    return;
                }

                mainActivityView.onSuccessGetUserInfo(userInfoResponse.getUserInfoResult());
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mainActivityView.onFailureGetUserInfo();

            }
        });
    }
}
