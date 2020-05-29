package com.softsquared.template.src.main.announcement.interfaces;

import com.softsquared.template.src.main.models.UserInfo;

public interface AnnouncementActivityView {

    void onSuccessGetUserInfo(UserInfo myInfo);

    void onFailureGetUserInfo();
}
