package com.softsquared.template.src.login.interfaces;

import com.softsquared.template.src.login.models.LoginInfo;

public interface LoginActivityView {

    void LoginSuccess(LoginInfo loginInfo);

    void LoginFailure(String message);
}
