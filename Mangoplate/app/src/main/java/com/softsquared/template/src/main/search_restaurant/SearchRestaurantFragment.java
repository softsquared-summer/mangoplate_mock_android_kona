package com.softsquared.template.src.main.search_restaurant;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Paint;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softsquared.template.R;
import com.softsquared.template.src.main.GPSService;
import com.softsquared.template.src.main.MainActivity;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoInfo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.LongFunction;

import javax.net.ssl.SNIHostName;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class SearchRestaurantFragment extends Fragment implements SearchRestaurantActivityView {

    MainActivity mainActivity;
    int currentPage = 0;
    private ViewGroup mViewGroup;
    private ViewPager topViewPager;
    Context mContext;
    SearchRestaurantActivityView searchRestaurantActivityView;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;
    SearchRestaurantService searchRestaurantService;
    SearchRestaurantRecyclerAdapter searchRestaurantRecyclerAdapter;
    RecyclerView searchRestaurantRecyclerview;
    GridLayoutManager gridLayoutManager;
    LocationManager locationManager;
    GPSService gpsService;
    TimerTask timerTask;
    Timer timer;
    private boolean topBannerReady;
    private Runnable runnable;
    private static final int REQUEST_CODE_LOCATION = 2;

    public static SearchRestaurantFragment newInstance()
    {
        return new SearchRestaurantFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mainActivity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewGroup = (ViewGroup) inflater.inflate(R.layout.search_restaurant_layout, container, false);
        gpsService = new GPSService(getContext());

        searchRestaurantService = new SearchRestaurantService(this);
        underline();
        setTopPhoto();
        setBannerTimer();
        init();
        searchRestaurantService.getRestaurantList();


        return mViewGroup;
    }

// 단어 밑줄 긋기
    private void underline()
    {
        TextView location_textView = mViewGroup.findViewById(R.id.search_restaurant_location_textView);
        TextView search_category = mViewGroup.findViewById(R.id.search_category);

        location_textView.setPaintFlags(location_textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        search_category.setPaintFlags(search_category.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    // 음식점 찾는 부분 리사이클러뷰 장착
    private void init()
    {
        searchRestaurantRecyclerview = mViewGroup.findViewById(R.id.search_restaurant_recyclerView);
        gridLayoutManager = new GridLayoutManager(mainActivity, 2);
        searchRestaurantRecyclerview.setLayoutManager(gridLayoutManager);
        searchRestaurantRecyclerAdapter = new SearchRestaurantRecyclerAdapter(mainActivity);
        searchRestaurantRecyclerview.setAdapter(searchRestaurantRecyclerAdapter);
        Log.e("리사이클러 뷰 어댑터 장착 완료", "어댑");
    }


    // 상단 광고 부분 어댑터 장착
    private void setTopPhoto()
    {
        mainActivity.showProgressDialog();

        PagerAdapter pagerAdapter = new SearchRestaurantTopAdAdapter(getContext());
        topViewPager = mViewGroup.findViewById(R.id.search_restaurant_ad_viewPager);
        topViewPager.setAdapter(pagerAdapter);
        Log.e("Topphoto", "어댑터 장착완료");

        final SearchRestaurantService searchRestaurantService = new SearchRestaurantService(this);
    }

    // 상단 광고 부분 사진 넘어가는 시간
    private void setBannerTimer()
    {
        final long DELAY = 3000;
        final long STAY = 3000;

        Handler handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if(currentPage == 7)
                {
                    currentPage = 0;
                }
                topViewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY, STAY);
    }

    @Override
    public void onResume() {
        super.onResume();
        setBannerTimer();
    }

    @Override
    public void onStop() {
        super.onStop();
//        searchRestaurantTopAdAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
//        searchRestaurantTopAdAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        searchRestaurantTopAdAdapter.notifyDataSetChanged();
        super.onDestroyView();
    }

    @Override
    public void GetTopPhotoOnSuccess(ArrayList<TopPhotoInfo> topPhotoInfoArrayList) {

    }

    @Override
    public void GetTopPhotoOnFailure() {
        mainActivity.hideProgressDialog();

    }

    @Override
    public void GetSearchRestaurantOnSuccess(SearchRestaurantResponse searchRestaurantResponse) {
        searchRestaurantRecyclerAdapter.clear();

        for(SearchRestaurantInfo restaurantInfo : searchRestaurantResponse.getResult())
            searchRestaurantRecyclerAdapter.add(restaurantInfo);

        searchRestaurantRecyclerAdapter.notifyDataSetChanged();
        mainActivity.hideProgressDialog();

    }


    @Override
    public void GetSearchRestaurantOnFailure() {
        mainActivity.hideProgressDialog();
    }
}
