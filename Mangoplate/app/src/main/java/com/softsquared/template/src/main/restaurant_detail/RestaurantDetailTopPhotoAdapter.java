package com.softsquared.template.src.main.restaurant_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailTopPhotoInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantDetailTopPhotoAdapter extends RecyclerView.Adapter<RestaurantDetailTopPhotoAdapter.RestaurantDetailTopPhotoViewHolder> {

    private ArrayList<RestaurantDetailTopPhotoInfo> topPhotoInfoArrayList = new ArrayList<>();

    public RestaurantDetailTopPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_detail_top_photo_item, parent, false);
        return new RestaurantDetailTopPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailTopPhotoViewHolder holder, int position) {
        holder.setting(topPhotoInfoArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return Math.min(topPhotoInfoArrayList.size(), 5);
    }

    public void add(RestaurantDetailTopPhotoInfo info)
    {
        topPhotoInfoArrayList.add(info);
    }

    public void clear()
    {
        topPhotoInfoArrayList.clear();
    }

    public class RestaurantDetailTopPhotoViewHolder  extends RecyclerView.ViewHolder{
        ImageView topPhotoImageView;

        public RestaurantDetailTopPhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            topPhotoImageView = itemView.findViewById(R.id.restaurant_top_photo_item);
        }

        void setting(RestaurantDetailTopPhotoInfo restaurantDetailTopPhotoInfo)
        {
            Glide.with(itemView.getContext()).load(restaurantDetailTopPhotoInfo.getImageUrl()).into(topPhotoImageView);
        }
    }
}
