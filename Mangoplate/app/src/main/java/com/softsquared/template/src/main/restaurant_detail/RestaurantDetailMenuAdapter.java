package com.softsquared.template.src.main.restaurant_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailMenuInfo;
import com.softsquared.template.src.main.restaurant_detail.models.RestaurantDetailTopPhotoInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantDetailMenuAdapter extends RecyclerView.Adapter<RestaurantDetailMenuAdapter.RestaurantDetailMenuViewHolder> {

    private ArrayList<RestaurantDetailMenuInfo> menuInfoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public RestaurantDetailMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_detail_menu_item, parent, false);
        return new RestaurantDetailMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailMenuViewHolder holder, int position) {
        holder.setting(menuInfoArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return menuInfoArrayList.size();
    }

    public void add(RestaurantDetailMenuInfo info)
    {
        menuInfoArrayList.add(info);
    }

    public void clear(){
        menuInfoArrayList.clear();
    }

    public class RestaurantDetailMenuViewHolder extends RecyclerView.ViewHolder{
        TextView menuNameTextView;
        TextView menuPriceTextView;

        public RestaurantDetailMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuNameTextView = itemView.findViewById(R.id.restaurant_detail_menu_name);
            menuPriceTextView = itemView.findViewById(R.id.restaurant_detail_menu_price);
        }

        void setting(RestaurantDetailMenuInfo restaurantDetailMenuInfo)
        {
            menuNameTextView.setText(restaurantDetailMenuInfo.getName());
            menuPriceTextView.setText(restaurantDetailMenuInfo.getPrice());
        }
    }
}
