package com.softsquared.template.src.main.search_restaurant;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;
import com.softsquared.template.src.main.MainActivity;
import com.softsquared.template.src.main.restaurant_detail.RestaurantDetailActivity;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SearchRestaurantRecyclerAdapter extends RecyclerView.Adapter<SearchRestaurantRecyclerAdapter.SearchRestaurantViewHolder> {

    private ArrayList<SearchRestaurantInfo> searchRestaurantInfoArrayList = new ArrayList<>();

    private SearchRestaurantFragment searchRestaurantFragment;
    private MainActivity mainActivity;

    public SearchRestaurantRecyclerAdapter(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }


    @NonNull
    @Override
    public SearchRestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_restaurant_recyclerview_item, parent, false);

        return new SearchRestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRestaurantViewHolder holder, int position) {
        holder.setting(searchRestaurantInfoArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return searchRestaurantInfoArrayList.size();
    }

    public void add(SearchRestaurantInfo searchRestaurantInfo)
    {
        searchRestaurantInfoArrayList.add(searchRestaurantInfo);
    }

    public void clear()
    {
        searchRestaurantInfoArrayList.clear();
    }

    public class SearchRestaurantViewHolder extends RecyclerView.ViewHolder{

        private String area;
        private int restaurantId;
        private String img;
        private String star;
        private String title;
        private String distance;
        private String seenNum;
        private String reviewNum;
        private String rating;
        private String ratingColor;

        ImageView restaurant_image;
        TextView restaurant_name;
        TextView restaurant_distance;
        TextView restaurant_views;
        TextView restaurant_modify;
        TextView restaurant_rate;


        public SearchRestaurantViewHolder(@NonNull final View itemView) {
            super(itemView);
            restaurant_image = itemView.findViewById(R.id.search_restaurant_img);
            restaurant_name = itemView.findViewById(R.id.search_restaurant_name);
            restaurant_distance = itemView.findViewById(R.id.search_restaurant_distance);
            restaurant_views = itemView.findViewById(R.id.search_restaurant_views);
            restaurant_modify = itemView.findViewById(R.id.search_restaurant_modify);
            restaurant_rate = itemView.findViewById(R.id.search_restaurant_rate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        Intent intent  = new Intent(itemView.getContext(), RestaurantDetailActivity.class);
                        intent.putExtra("restaurantId", restaurantId);
                        Log.e(" resid", "" +restaurantId);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });

        }

        void setting(SearchRestaurantInfo searchRestaurantInfo)
        {
            Glide.with(itemView.getContext()).load(searchRestaurantInfo.getImg()).into(restaurant_image);

            restaurantId = searchRestaurantInfo.getRestaurantId();
            restaurant_name.setText(searchRestaurantInfo.getTitle());
            restaurant_distance.setText(searchRestaurantInfo.getArea() + " - " + searchRestaurantInfo.getDistance());
            restaurant_views.setText(searchRestaurantInfo.getSeenNum());
            restaurant_modify.setText(searchRestaurantInfo.getReviewNum());
            restaurant_rate.setText(searchRestaurantInfo.getRating());
            switch (searchRestaurantInfo.getRatingColor()) {
                case "orange":
                {
                    restaurant_rate.setVisibility(View.VISIBLE);
                    restaurant_rate.setTextColor(itemView.getResources().getColor(R.color.orange_red));
                    break;
                }
                case "gray":
                {
                    restaurant_rate.setVisibility(View.VISIBLE);
                    restaurant_rate.setTextColor(itemView.getResources().getColor(R.color.gray));
                    break;
                }
                case "":
                {
                    restaurant_rate.setVisibility(View.INVISIBLE);
                    break;
                }
            }

        }
    }
}
