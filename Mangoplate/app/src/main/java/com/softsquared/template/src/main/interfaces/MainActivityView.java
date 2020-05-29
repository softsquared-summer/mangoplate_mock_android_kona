package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.main.models.UserInfo;

public interface MainActivityView {

    void onSuccessGetUserInfo(UserInfo myInfo);

    void onFailureGetUserInfo();
}
