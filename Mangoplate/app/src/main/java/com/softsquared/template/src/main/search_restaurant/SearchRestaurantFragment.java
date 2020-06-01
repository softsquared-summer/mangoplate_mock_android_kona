package com.softsquared.template.src.main.search_restaurant;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.template.R;
import com.softsquared.template.src.ApplicationClass;
import com.softsquared.template.src.main.GPSService;
import com.softsquared.template.src.main.MainActivity;
import com.softsquared.template.src.main.search_restaurant.interfaces.SearchRestaurantActivityView;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantResponse;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import static com.softsquared.template.src.ApplicationClass.TAG;

public class SearchRestaurantFragment extends Fragment implements SearchRestaurantActivityView {

    MainActivity mainActivity;
    private ViewGroup mViewGroup;
    private ViewPager mViewPager;
    Context mContext;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;
    SearchRestaurantService searchRestaurantService;
    SearchRestaurantRecyclerAdapter searchRestaurantRecyclerAdapter;
    RecyclerView searchRestaurantRecyclerview;
    GridLayoutManager gridLayoutManager;
    LocationManager locationManager;
    GPSService gpsService;
    private static final int REQUEST_CODE_LOCATION = 2;

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
        //getMyLocation();
        //setMyLocation();
        searchRestaurantService = new SearchRestaurantService(this);
        topViewPager();

        //initView();


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
        showProgressDialog();
        searchRestaurantService.getSearchRestaurant();
    }

    private void topViewPager()
    {
        mViewPager = mViewGroup.findViewById(R.id.search_restaurant_ad_viewPager);
        PagerAdapter pagerAdapter = new SearchRestaurantTopAdAdapter(getContext());
        mViewPager.setAdapter(pagerAdapter);

        searchRestaurantService = new SearchRestaurantService(this);
        //searchRestaurantService.getTopPhoto();

    }

    /*public void restaurantList()
    {
        Log.e(TAG, ""+ gpsService.getLatitude() + ", " + gpsService.getLongitude());
        searchRestaurantService.getSearchRestaurant((float) gpsService.getLatitude(), (float)gpsService.getLongitude(),
                "금천구", "평점순", "전체", "한식", null, null,"3", null, null );
        mainActivity.showProgressDialog();
    }*/



    public ProgressDialog mProgressDialog;
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    void setMyLocation() {
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        Location userLocation = getMyLocation();
        if (userLocation != null) {
            double latitude = userLocation.getLatitude();
            double longitude = userLocation.getLongitude();
            //Intent locationIntent = new Intent(getActivity(), MyLocationSearch.class);
            ApplicationClass.lat = (float) latitude;
            ApplicationClass.lng = (float) longitude;
           // locationIntent.putExtra("lat", ApplicationClass.lat);
           // locationIntent.putExtra("lng", ApplicationClass.lng);
        }
    }

    private Location getMyLocation() {
        Location currentLocation = null;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        } else {

            String locationProvider = LocationManager.GPS_PROVIDER;
            //마지막 위치만 받아오는 기능임 . 갱신하는 콜백을 받아오게 하는 코드가 존재해야한다.
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
            }
        }
        return currentLocation;
    }
    @Override
    public void GetTopPhotoOnSuccess(ArrayList<TopPhotoInfo> topPhotoInfoArrayList) {

    }

    @Override
    public void GetTopPhotoOnFailure() {

    }

    @Override
    public void GetSearchRestaurantOnSuccess(SearchRestaurantResponse searchRestaurantResponse) {
       /* initView();
        for (SearchRestaurantInfo result : searchRestaurantResponse.getResult()) {

            searchRestaurantRecyclerAdapter.add(result);

        }*/
    }

    /*@Override
    public void GetSearchRestaurantOnSuccess(ArrayList<SearchRestaurantInfo> searchRestaurantInfoArrayList) {

        for(SearchRestaurantInfo searchRestaurantInfo : searchRestaurantInfoArrayList)
            searchRestaurantRecyclerAdapter.add(searchRestaurantInfo);
        searchRestaurantRecyclerAdapter.notifyDataSetChanged();

        mainActivity.hideProgressDialog();
        for(int i = 0; i < searchRestaurantInfoArrayList.size(); i++){
            searchRestaurantInfoArrayList.add(searchRestaurantInfoArrayList.get(i));
        }
        searchRestaurantRecyclerAdapter.notifyDataSetChanged();
    }*/

    @Override
    public void GetSearchRestaurantOnFailure() {
    }
}
