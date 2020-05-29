package com.softsquared.template.src.main.announcement.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.template.src.main.models.UserInfo;

import java.util.ArrayList;

public class AnnouncementResponse {


    public ArrayList<AnnouncementInfo> getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @SerializedName("result")
    private ArrayList<AnnouncementInfo> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

}