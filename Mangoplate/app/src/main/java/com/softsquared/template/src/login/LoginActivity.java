package com.softsquared.template.src.login;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.login.interfaces.LoginActivityView;
import com.softsquared.template.src.login.models.LoginInfo;
import com.softsquared.template.src.main.MainActivity;

import java.util.Arrays;
import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private FacebookLoginCallback facebookLoginCallback;
    private ImageButton facebookLoginBtn;
    private CallbackManager callbackManager;
    LoginService loginService;
    AccessToken accessToken;
    private ImageView loginBackgoundImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginService = new LoginService(this);

        loginBackgoundImage = findViewById(R.id.login_background_img);
        loginBackgoundImage.setColorFilter(getResources().getColor(R.color.colorHexGray), PorterDuff.Mode.MULTIPLY);


        FacebookLogin();
    }

    public void FacebookLogin()
    {
        callbackManager = CallbackManager.Factory.create();
        facebookLoginCallback = new FacebookLoginCallback();

        facebookLoginBtn = findViewById(R.id.login_facebook_btn);


        facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
                accessToken = AccessToken.getCurrentAccessToken();
                if(accessToken != null)
                {
                    accessToken = AccessToken.getCurrentAccessToken();
                    tryPost("facebook", accessToken.getToken());
                    loginManager.registerCallback(callbackManager, facebookLoginCallback);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void LoginSuccess(LoginInfo loginInfo) {

        sSharedPreferences.edit().putString(X_ACCESS_TOKEN, loginInfo.getJwt()).apply();

        Log.e(TAG, "로그인 성공");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void tryPost(String type, String jwtToken)
    {
        showProgressDialog();
        loginService.postFacebook(jwtToken);
    }

    @Override
    public void LoginFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

}
