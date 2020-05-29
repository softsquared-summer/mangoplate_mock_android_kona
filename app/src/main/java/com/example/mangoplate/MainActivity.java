package com.example.mangoplate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> imageList;
    private static final int DP = 24;

    private RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    GridLayoutManager layoutManager;

    //하단 네비게이션 뷰  관련
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();
    SearchFragment searchFragment = new SearchFragment();
    MypageFragment mypageFragment = new MypageFragment();
    MainActivityPagerAdapter mainActivityPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navi);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);


        mBottomNavigationView();

        this.initializeData();

//        ViewPager viewPager = findViewById(R.id.ad_viewPager);
//        viewPager.setClipToPadding(false);
//
//        recyclerView = findViewById(R.id.restaurant_recyclerView);
//
//
//        viewPager.setAdapter(new ViewPagerAdapter(this, imageList));
    }

    public void mBottomNavigationView()
    {
        mViewPager = findViewById(R.id.viewPager);
        mainActivityPagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager(), 4);
        mViewPager.setAdapter(mainActivityPagerAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search: {
                        mViewPager.setCurrentItem(0);
                    }
                    case R.id.discount:{
                        mViewPager.setCurrentItem(1);
                    }
                    case R.id.notice:{
                        mViewPager.setCurrentItem(2);
                    }
                    case R.id.myprofile: {
                        mViewPager.setCurrentItem(3);
                    }
                }
                return true;
            }
        });

    }
    public void initializeData()
    {
        imageList = new ArrayList<>();

        imageList.add(R.drawable.ad_image1);
        imageList.add(R.drawable.ad_image2);
        imageList.add(R.drawable.ad_image3);
        imageList.add(R.drawable.ad_image4);
        imageList.add(R.drawable.ad_image5);
        imageList.add(R.drawable.ad_image6);
        imageList.add(R.drawable.ad_image7);
    }

}
