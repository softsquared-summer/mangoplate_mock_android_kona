package com.softsquared.template.src.main.search_restaurant;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.template.R;
import com.softsquared.template.src.main.GPSService;
import com.softsquared.template.src.main.MainActivity;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoInfo;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class SearchRestaurantFragment extends Fragment implements SearchRestaurantActivityView {

    MainActivity mainActivity;
    private ViewGroup mViewGroup;
    private ViewPager2 topViewPager;
    Context mContext;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;
    SearchRestaurantService searchRestaurantService;
    SearchRestaurantRecyclerAdapter searchRestaurantRecyclerAdapter;
    RecyclerView searchRestaurantRecyclerview;
    GridLayoutManager gridLayoutManager;
    LocationManager locationManager;
    GPSService gpsService;
    TimerTask timerTask;
    private boolean topBannerReady;
    private static final int REQUEST_CODE_LOCATION = 2;

    public static SearchRestaurantFragment newInstance()
    {
        return new SearchRestaurantFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        mainActivity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewGroup = (ViewGroup) inflater.inflate(R.layout.search_restaurant_layout, container, false);


        gpsService = new GPSService(getContext());
        searchRestaurantService = new SearchRestaurantService(this);
        setTopPhoto();
        setBannerTimer();

        return mViewGroup;
    }

    private void initView()
    {
        int column = 2;
        searchRestaurantRecyclerview = mViewGroup.findViewById(R.id.search_restaurant_recyclerView);
        gridLayoutManager = new GridLayoutManager(getContext(), column);
        searchRestaurantRecyclerview.setLayoutManager(gridLayoutManager);
        searchRestaurantRecyclerAdapter = new SearchRestaurantRecyclerAdapter(this);
        searchRestaurantRecyclerview.setAdapter(searchRestaurantRecyclerAdapter);
//        showProgressDialog();
//        searchRestaurantService.getSearchRestaurant();
    }


    private void setTopPhoto()
    {
        mainActivity.showProgressDialog();

        searchRestaurantTopAdAdapter = new SearchRestaurantTopAdAdapter();
        topViewPager = mViewGroup.findViewById(R.id.search_restaurant_ad_viewPager);
        topViewPager.setAdapter(searchRestaurantTopAdAdapter);

        final SearchRestaurantService searchRestaurantService = new SearchRestaurantService(this);
        topViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null)
                {
                    mainActivity.mainViewpager.setUserInputEnabled(true);
                }
            }
        });

    }

    private void setBannerTimer()
    {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                int index = topViewPager.getCurrentItem();
                int adcount = searchRestaurantTopAdAdapter.getItemCount();
                int nextindex = (index + 1) % adcount;
                new Handler(Looper.getMainLooper())
                        .postDelayed(() -> topViewPager.setCurrentItem(nextindex), 2000);

            }
        };
        new Timer().schedule(timerTask, 0, 2000);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(topBannerReady)
        {
            if(timerTask == null)
                setBannerTimer();
            else
                timerTask.run();
        }

    }

    @Override
    public void GetTopPhotoOnSuccess(ArrayList<TopPhotoInfo> topPhotoInfoArrayList) {
        for(TopPhotoInfo b : topPhotoInfoArrayList)
            searchRestaurantTopAdAdapter.add(b);

        searchRestaurantTopAdAdapter.notifyDataSetChanged();
        topBannerReady = true;
        setBannerTimer();

        mainActivity.hideProgressDialog();

    }

    @Override
    public void GetTopPhotoOnFailure() {
        mainActivity.hideProgressDialog();

    }

    @Override
    public void GetSearchRestaurantOnSuccess(ArrayList<SearchRestaurantInfo> restaurantInfos) {
        searchRestaurantRecyclerAdapter.clear();

        for(SearchRestaurantInfo restaurantInfo : restaurantInfos)
            searchRestaurantRecyclerAdapter.add(restaurantInfo);

        searchRestaurantRecyclerAdapter.notifyDataSetChanged();
        mainActivity.hideProgressDialog();

    }



    @Override
    public void GetSearchRestaurantOnFailure() {
        mainActivity.hideProgressDialog();
    }
}
