package com.softsquared.template.src.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.models.UserInfo;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView {


    final MainService mainService = new MainService(this);

    //하단 네비게이션 뷰  관련
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();
    SearchRestaurantFragment searchRestaurantFragment = new SearchRestaurantFragment();
//    MypageFragment mypageFragment = new MypageFragment();
    MainContentsAdapter mainContentsAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView();

        mainService.getMyInfo();
    }

    public void mBottomNavigationView()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mViewPager = findViewById(R.id.viewPager);
        mainContentsAdapter = new MainContentsAdapter(getSupportFragmentManager(), 2);
        mViewPager.setAdapter(mainContentsAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search: {
                        mViewPager.setCurrentItem(0);
                        break;
                    }
                    case R.id.discount:{
                        mViewPager.setCurrentItem(1);
                        break;
                    }
                    case R.id.notice:{
                        mViewPager.setCurrentItem(2);
                        break;
                    }
                    case R.id.myprofile: {
                        mViewPager.setCurrentItem(3);
                        break;
                    }
                }
                return true;
            }
        });

    }
    
    @Override
    public void onSuccessGetUserInfo(UserInfo myInfo) {
        Log.d(TAG, "name : " + myInfo.getName());
        Log.d(TAG, "email : " + myInfo.getEmail());
        Log.d(TAG, "phone : " + myInfo.getPhone());
        Log.d(TAG, "profileUrl : " + myInfo.getProfileUrl());

        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("name", myInfo.getName());
        editor.putString("email", myInfo.getEmail());
        editor.putString("phone", myInfo.getPhone());
        editor.putString("profileUrl", myInfo.getProfileUrl());
        editor.apply();
    }

    @Override
    public void onFailureGetUserInfo() {
        Log.d(TAG, "MainActivity::onFailureGetUser()");
    }
}
