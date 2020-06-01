package com.softsquared.template.src.main;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.models.UserInfo;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import java.security.MessageDigest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import it.sephiroth.android.library.viewrevealanimator.ViewRevealAnimator;

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
    ViewRevealAnimator viewRevealAnimator;
    ViewPager mViewPager;
    FloatingActionButton floatingActionButton, floatingActionCloseButton;
    Animation fadeIn, fadeOut,rise, rotation;

    private TextView floating_eat_deal, floating_gone, floating_review, floating_register_res;
    private ImageView floating_eat_deal_img, floating_gone_img, floating_review_img, floating_register_res_img;

    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView();
        animationSetting();
        floatinAnim();

    }

    public void mBottomNavigationView()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mViewPager = findViewById(R.id.viewPager);
        mainContentsAdapter = new MainContentsAdapter(getSupportFragmentManager(), 4);
        mViewPager.setAdapter(mainContentsAdapter);
        floatingActionCloseButton = findViewById(R.id.middle_floating_action_close_button);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
                    case R.id.plus_btn:
                    {
//                        AnimationSet appearAnimation = new AnimationSet(true);
//                        appearAnimation.addAnimation(fadeIn);
//                        appearAnimation.addAnimation(rise);
//
//                        floatingActionButton.startAnimation(fadeOut);
//                        floatingActionCloseButton.startAnimation(appearAnimation);
//
//
//                        floating_eat_deal.startAnimation(appearAnimation);
//                        floating_eat_deal_img.startAnimation(appearAnimation);
//                        floating_gone.startAnimation(appearAnimation);
//                        floating_gone_img.startAnimation(appearAnimation);
//                        floating_register_res.startAnimation(appearAnimation);
//                        floating_register_res_img.startAnimation(appearAnimation);
//                        floating_review.startAnimation(appearAnimation);
//                        floating_review_img.startAnimation(appearAnimation);

                        floatingActionButton = findViewById(R.id.middle_floating_action_button);
                        viewRevealAnimator = findViewById(R.id.animator);
                        viewRevealAnimator.setDisplayedChild(viewRevealAnimator.getDisplayedChild() - 1, false,
                                new Point((int)floatingActionButton.getX(), (int)floatingActionButton.getY()));

                        floatingClose();


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

    private void animationSetting()
    {
        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        rise = new TranslateAnimation(0,0,200,0);
        rotation = new RotateAnimation(0, 45, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);

    }

    private void floatinAnim()
    {
//        floating_eat_deal = findViewById(R.id.float_go_to_eatdeal_textview);
//        floating_gone = findViewById(R.id.float_gone_textview);
//        floating_review = findViewById(R.id.float_write_review_textview);
//        floating_register_res = findViewById(R.id.float_register_restaurant_textview);
//        floating_eat_deal_img = findViewById(R.id.float_go_to_eatdeal_img);
//        floating_gone_img = findViewById(R.id.float_gone_img);
//        floating_review_img = findViewById(R.id.float_write_review_img);
//        floating_register_res_img = findViewById(R.id.float_register_restaurant_img);


    }

    private void floatingClose()
    {
        floatingActionCloseButton = findViewById(R.id.middle_floating_action_close_button);
        floatingActionCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRevealAnimator.setDisplayedChild(viewRevealAnimator.getDisplayedChild() - 1, false,
                        new Point((int)floatingActionButton.getX(), (int)floatingActionButton.getY()));
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
