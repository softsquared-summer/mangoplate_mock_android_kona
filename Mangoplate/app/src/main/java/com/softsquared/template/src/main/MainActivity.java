package com.softsquared.template.src.main;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.models.UserInfo;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
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
    public ViewPager2 mainViewpager;
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
        setViwewPager();
        mainService.getMyInfo();

    }

    public void setViwewPager()
    {

        mainViewpager = findViewById(R.id.viewPager);
        mainContentsAdapter = new MainContentsAdapter(this, 4);
        mainViewpager.setAdapter(mainContentsAdapter);

        mainViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 0) bottomNavigationView.setSelectedItemId(R.id.search);
                else if(position == 1) bottomNavigationView.setSelectedItemId(R.id.discount);
                else if(position == 2) bottomNavigationView.setSelectedItemId(R.id.notice);
                else if(position == 3) bottomNavigationView.setSelectedItemId(R.id.myprofile);
            }
        });
    }


    public void mBottomNavigationView()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        floatingActionCloseButton = findViewById(R.id.middle_floating_action_close_button);

//        Menu search = bottomNavigationView.getMenu();
//        Menu discount = bottomNavigationView.getMenu();
//        Menu notice = bottomNavigationView.getMenu();
//        Menu myprofile = bottomNavigationView.getMenu();
//        Glide.with(this).load(R.drawable.search_icon_btn).into(search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search: {
                        mainViewpager.setCurrentItem(0);
                        break;
                    }
                    case R.id.discount:{
                        mainViewpager.setCurrentItem(1);
                        break;
                    }
                    case R.id.plus_btn:
                    {

                        floatingActionButton = findViewById(R.id.middle_floating_action_button);
                        viewRevealAnimator = findViewById(R.id.animator);
                        viewRevealAnimator.setDisplayedChild(viewRevealAnimator.getDisplayedChild() - 1, false,
                                new Point((int)floatingActionButton.getX(), (int)floatingActionButton.getY()));

                        floatingClose();

                    }
                    case R.id.notice:{
                        mainViewpager.setCurrentItem(2);
                        break;
                    }
                    case R.id.myprofile: {
                        mainViewpager.setCurrentItem(3);
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
