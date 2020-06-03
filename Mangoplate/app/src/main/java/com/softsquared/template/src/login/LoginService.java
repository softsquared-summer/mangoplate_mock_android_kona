package com.softsquared.template.src.login;

import android.util.Log;

import com.softsquared.template.src.ApplicationClass;
import com.softsquared.template.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.template.src.login.models.DefaultResponse;
import com.softsquared.template.src.login.interfaces.LoginActivityView;
import com.softsquared.template.src.login.models.LoginBody;
import com.softsquared.template.src.login.models.LoginInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postFacebook(String loginType, String at) {
        final LoginRetrofitInterface mLoginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        mLoginRetrofitInterface.postFacebook(loginType, new LoginBody(at)).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if(defaultResponse == null)
                {
                    Log.e(TAG, "LoginResponse is null");
                    mLoginActivityView.LoginFailure();
                }
                else if (!defaultResponse.getIsSuccess())
                {
                    Log.e(TAG, "" + defaultResponse.getCode());
                    Log.e(TAG, "" + defaultResponse.getMessage());
                    mLoginActivityView.LoginFailure();
                }
                mLoginActivityView.LoginSuccess(defaultResponse.getResult());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mLoginActivityView.LoginFailure();
                Log.e(TAG,"로그인 실패");
            }
        });
    }
}
