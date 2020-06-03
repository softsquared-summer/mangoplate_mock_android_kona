package com.softsquared.template.src.main.restaurant_detail;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amar.library.ui.StickyScrollView;
import com.amar.library.ui.interfaces.IScrollViewListener;
import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.main.restaurant_detail.interfaces.RestaurantDetailActivityView;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailInfo;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailMenuInfo;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailTopPhotoInfo;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailActivityView {
    RestaurantDetailService restaurantDetailService;
    private RestaurantDetailTopPhotoAdapter restaurantDetailTopPhotoAdapter;
    private RestaurantDetailMenuAdapter restaurantDetailMenuAdapter;

    private StickyScrollView stickyScrollView;
    private int restaurantId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail_layout);

        Intent intent = getIntent();
        if(intent != null)
        {
            restaurantId = intent.getIntExtra("restaurantId", restaurantId);
            restaurantDetailService = new RestaurantDetailService(this, restaurantId);
            restaurantDetailService.getRestaurantDetail();
        }

        StickyScrollView();
        SetRestaurantTopPhoto();
    }

    private void StickyScrollView()
    {
        final ConstraintLayout topBar = findViewById(R.id.restaurant_detail_top_bar);
        ConstraintLayout downArrow = findViewById(R.id.restaurant_detail_down_arrow_const);
        ConstraintLayout cameraBtn = findViewById(R.id.restaurant_detail_camera_const);
        ConstraintLayout linkBtn = findViewById(R.id.restaurant_detail_link_const);

        final ImageView downArrowImg = findViewById(R.id.restaurant_detail_down_arrow);
        final ImageView cameraImg = findViewById(R.id.restaurant_detail_camera);
        final ImageView linkImg = findViewById(R.id.restaurant_detail_link);

        stickyScrollView = findViewById(R.id.restaurant_detail_scrollView);
        stickyScrollView.setScrollViewListener(new IScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if(t > oldt && stickyScrollView.isHeaderSticky())
                {
                    topBar.setBackgroundColor(getResources().getColor(R.color.orange_red));
                    downArrowImg.setColorFilter(getResources().getColor(R.color.white));
                    cameraImg.setColorFilter(getResources().getColor(R.color.white));
                    linkImg.setColorFilter(getResources().getColor(R.color.white));


                }
                if(t < oldt && !stickyScrollView.isHeaderSticky())
                {
                    topBar.setBackgroundColor(getResources().getColor(R.color.white));
                    downArrowImg.clearColorFilter();
                    cameraImg.clearColorFilter();
                    linkImg.clearColorFilter();
                }
            }
            @Override
            public void onScrollStopped(boolean isStoped) {
            }
        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void SetRestaurantTopPhoto()
    {
        restaurantDetailTopPhotoAdapter = new RestaurantDetailTopPhotoAdapter();
        RecyclerView topPhotoRecyclerView = findViewById(R.id.restaurant_detail_top_photo_recyclerView);
        topPhotoRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        topPhotoRecyclerView.setAdapter(restaurantDetailTopPhotoAdapter);
    }


    @Override
    public void GetRestaurantDetailOnSuccess(RestaurantDetailInfo restaurantDetailInfo) {
        restaurantDetailTopPhotoAdapter.clear();
        ArrayList<RestaurantDetailTopPhotoInfo> topPhotoInfoArrayList = restaurantDetailInfo.getImages();
        for(RestaurantDetailTopPhotoInfo restaurantDetailTopPhotoInfo : topPhotoInfoArrayList)
        {
            restaurantDetailTopPhotoAdapter.add(restaurantDetailTopPhotoInfo);
        }

        restaurantDetailTopPhotoAdapter.notifyDataSetChanged();

        TextView restaurant_detail_name_textview = findViewById(R.id.restaurant_detail_name);
        TextView restaurant_detail_views_textview = findViewById(R.id.restaurant_detail_views_textView);
        TextView restaurant_detail_star_textview = findViewById(R.id.restaurant_detail_star_textView);
        TextView restaurant_detail_modify_textview = findViewById(R.id.restaurant_detail_modify_textView);
        TextView restaurant_detail_rate_textview = findViewById(R.id.restaurant_detail_rate_textView);

        restaurant_detail_name_textview.setText(restaurantDetailInfo.getName());
        restaurant_detail_views_textview.setText(restaurantDetailInfo.getSeenNum());
        restaurant_detail_star_textview.setText(restaurantDetailInfo.getStarNum());
        restaurant_detail_modify_textview.setText(restaurantDetailInfo.getReviewNum());
        restaurant_detail_rate_textview.setText(restaurantDetailInfo.getRating());
        switch (restaurantDetailInfo.getRatingColor()) {
            case "orange":
                {
                    restaurant_detail_rate_textview.setVisibility(View.VISIBLE);
                    restaurant_detail_rate_textview.setTextColor(getResources().getColor(R.color.orange_red));
                    break;
                }
            case "gray":
            {
                restaurant_detail_rate_textview.setVisibility(View.VISIBLE);
                restaurant_detail_rate_textview.setTextColor(getResources().getColor(R.color.gray));
                break;
            }
            case "":
            {
                restaurant_detail_rate_textview.setVisibility(View.INVISIBLE);
                break;
            }
        }

        TextView restaurant_detail_location_textview = findViewById(R.id.restaurant_detail_location_textView);
        TextView restaurant_detail_location_jibun_textview = findViewById(R.id.restaurant_detail_location_jibun_textView);
        restaurant_detail_location_textview.setText(restaurantDetailInfo.getAddress());
        restaurant_detail_location_jibun_textview.setText(restaurantDetailInfo.getOldAddress());

        TextView restaurant_detail_resinfo_update_date = findViewById(R.id.restaurant_detail_resinfo_update_date);
        TextView restaurant_detail_resinfo_opentime = findViewById(R.id.restaurant_detail_resinfo_opentime_contents);
        TextView restaurant_detail_resinfo_breaktime = findViewById(R.id.restaurant_detail_resinfo_breaktime_contents);
        TextView restaurant_detail_resinfo_caution = findViewById(R.id.restaurant_detail_resinfo_caution_textview);
        TextView restaurant_detail_resinfo_price = findViewById(R.id.restaurant_detail_resinfo_price_contents);

        restaurant_detail_resinfo_update_date.setText(restaurantDetailInfo.getInfoUpdate());
        restaurant_detail_resinfo_opentime.setText(restaurantDetailInfo.getInfoTime());
        restaurant_detail_resinfo_breaktime.setText(restaurantDetailInfo.getInfoHoliday());
        restaurant_detail_resinfo_caution.setText(restaurantDetailInfo.getInfoDescription());
        restaurant_detail_resinfo_price.setText(restaurantDetailInfo.getInfoDescription());

        ArrayList<RestaurantDetailMenuInfo> restaurantDetailMenuInfoArrayList = restaurantDetailInfo.getMenu();
//        restaurantDetailMenuAdapter.clear();
        for(RestaurantDetailMenuInfo restaurantDetailMenuInfo : restaurantDetailMenuInfoArrayList)
        {
            restaurantDetailMenuAdapter.add(restaurantDetailMenuInfo);
        }

//        restaurantDetailMenuAdapter.notifyDataSetChanged();


    }

    @Override
    public void GetRestaurantDetailOnFailure() {

    }


}
