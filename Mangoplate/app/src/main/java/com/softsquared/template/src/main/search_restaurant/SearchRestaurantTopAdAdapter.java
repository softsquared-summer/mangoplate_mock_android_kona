package com.softsquared.template.src.main.search_restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;
import com.softsquared.template.src.main.MainActivity;
import com.softsquared.template.src.main.search_restaurant.models.TopPhotoInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

public class SearchRestaurantTopAdAdapter extends RecyclerView.Adapter<SearchRestaurantTopAdAdapter.AdViewHolder> {
/*
    private Context mContext = null;

    public static final int dining_week = 0;
    public static final int cashback = 1;
    public static final int new_eat_deal = 2;
    public static final int visa = 3;
    public static final int hottest_awards = 4;
    public static final int best25 = 5;
    public static final int brunch7 = 6;

    public SearchRestaurantTopAdAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        if(mContext != null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.search_restaurant_ad_viewpager, null);
            ImageView imageView = view.findViewById(R.id.ad_imageView);
            switch(position)
            {
                case dining_week:
                    Glide.with(mContext).load(R.drawable.ad_image1).into(imageView);
                    break;
                case cashback:
                    Glide.with(mContext).load(R.drawable.ad_image2).into(imageView);
                    break;
                case new_eat_deal:
                    Glide.with(mContext).load(R.drawable.ad_image3).into(imageView);
                    break;
                case visa:
                    Glide.with(mContext).load(R.drawable.ad_image4).into(imageView);
                    break;
                case hottest_awards:
                    Glide.with(mContext).load(R.drawable.ad_image5).into(imageView);
                    break;
                case best25:
                    Glide.with(mContext).load(R.drawable.ad_image6).into(imageView);
                    break;
                case brunch7:
                    Glide.with(mContext).load(R.drawable.ad_image7).into(imageView);
                    break;
            }
        }
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

 */
private ArrayList<TopPhotoInfo> topPhotoInfoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_restaurant_ad_viewpager, parent, false);
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        holder.bind(topPhotoInfoArrayList.get(position));
    }

    public void add(TopPhotoInfo topPhotoInfo) {
        topPhotoInfoArrayList.add(topPhotoInfo);
    }

    @Override
    public int getItemCount() {
        return topPhotoInfoArrayList.size();
    }


    class AdViewHolder extends RecyclerView.ViewHolder {
        ImageView topPhoto;

        AdViewHolder(@NonNull View itemView) {
            super(itemView);
            topPhoto = itemView.findViewById(R.id.ad_imageView);
        }

        void bind(TopPhotoInfo topPhotoInfo) {
            Glide.with(itemView.getContext())
                    .load(topPhotoInfo.getImageUrl())
                    .into(topPhoto);

            /*itemView.setOnTouchListener((v, event) -> {
                Context context = v.getContext();
                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.mViewPager.setUserInputEnabled(false);
                }
                v.performClick();
                return false;
            });*/
        }
    }
}

