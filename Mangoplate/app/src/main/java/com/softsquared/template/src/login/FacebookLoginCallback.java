package com.softsquared.template.src.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.softsquared.template.src.main.MainActivity;

import org.json.JSONObject;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.sSharedPreferences;

public class FacebookLoginCallback implements FacebookCallback<LoginResult> {

    private Activity activity;
    FacebookLoginCallback(Activity activity)
    {
        this.activity = activity;
    }


    @Override
    public void onSuccess(LoginResult loginResult) {
        String accessToken = AccessToken.getCurrentAccessToken().getToken();
        Log.e("Callback :: ", "onSuccess");
        if(activity instanceof LoginActivity){
            final LoginService loginService = new LoginService((LoginActivity) activity);
            loginService.postFacebook("facebook", accessToken);
        }

        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("Facebook result",object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    public void onCancel() {
        Log.e("Callback :: ", "onCancel");
    }

    @Override
    public void onError(FacebookException error) {
        Log.e("Callback :: ", "onError : " + error.getMessage());
    }

//    public void requestMe(AccessToken token) {
//        GraphRequest graphRequest = GraphRequest.newMeRequest(token,
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.e("result",object.toString());
//                    }
//                });
//
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,email,gender,birthday");
//        String name = parameters.getString("name");
//        sSharedPreferences.edit().putString("name", name);
//        graphRequest.setParameters(parameters);
//        graphRequest.executeAsync();
//    }
}
