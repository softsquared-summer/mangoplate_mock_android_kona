package com.softsquared.template.src.main.discount.eatdeal;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.template.R;
import com.softsquared.template.src.main.discount.eatdeal.models.EatdealInfo;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantRecyclerAdapter;
import com.softsquared.template.src.main.search_restaurant.models.SearchRestaurantInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EatdealRecyclerAdapter extends RecyclerView.Adapter<EatdealRecyclerAdapter.EatdealViewholder> {

    private ArrayList<EatdealInfo> eatdealInfoArrayList = new ArrayList<>();

    EatdealFragment eatdealFragment;

    public EatdealRecyclerAdapter(EatdealFragment eatdealFragment)
    {
        this.eatdealFragment = eatdealFragment;
    }

    @NonNull
    @Override
    public EatdealViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_eatdeal_recyclerview_item, parent, false);

        return new EatdealRecyclerAdapter.EatdealViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EatdealViewholder holder, int position) {
        holder.setting(eatdealInfoArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return eatdealInfoArrayList.size();
    }

    public void add(EatdealInfo eatdealInfo)
    {
        eatdealInfoArrayList.add(eatdealInfo);
    }

    public void clear()
    {
        eatdealInfoArrayList.clear();
    }

    public class EatdealViewholder extends RecyclerView.ViewHolder {

        private ImageView discount_eatdeal_img;

        private ImageView discount_eatdeal_status_img;
        private TextView discount_eatdeal_status;
        private TextView discount_eatdeal_percent;
        private TextView discount_eatdeal_original_price;
        private TextView discount_eatdeal_sale_price;
        private TextView discount_eatdeal_title;
        private TextView discount_eatdeal_item;
        private TextView discount_eatdeal_description;


        public EatdealViewholder(@NonNull View itemView) {
            super(itemView);
            discount_eatdeal_img = itemView.findViewById(R.id.discount_eatdeal_item_img);
            discount_eatdeal_status = itemView.findViewById(R.id.discount_eatdeal_item_status);
            discount_eatdeal_status_img = itemView.findViewById(R.id.discount_eatdeal_item_status_img);
            discount_eatdeal_percent = itemView.findViewById(R.id.discount_eatdeal_item_percent);
            discount_eatdeal_original_price= itemView.findViewById(R.id.discount_eatdeal_item_originalprice);
            discount_eatdeal_sale_price = itemView.findViewById(R.id.discount_eatdeal_item_saleprice);
            discount_eatdeal_title = itemView.findViewById(R.id.discount_eatdeal_item_title);
            discount_eatdeal_item = itemView.findViewById(R.id.discount_eatdeal_item_item);
            discount_eatdeal_description = itemView.findViewById(R.id.discount_eatdeal_item_description_textview);

        }
        void setting(EatdealInfo eatdealInfo)
        {
            Glide.with(itemView.getContext()).load(eatdealInfo.getImageUrl()).into(discount_eatdeal_img);

            discount_eatdeal_percent.setText(eatdealInfo.getPercent());

            if(eatdealInfo.getStatus() == null)
            {
                discount_eatdeal_status.setVisibility(View.INVISIBLE);
                discount_eatdeal_status_img.setVisibility(View.INVISIBLE);
            }
            else if(eatdealInfo.getStatus() != null && eatdealInfo.getStatus().equals("재입고"))
            {
                discount_eatdeal_status.setText(eatdealInfo.getStatus());
                discount_eatdeal_status.setTextColor(itemView.getResources().getColor(R.color.black));
                discount_eatdeal_status_img.setImageResource(R.drawable.discount_label_shape_yellow);
            }
            else if(eatdealInfo.getStatus() != null && eatdealInfo.getStatus().equals("HOT"))
            {
                discount_eatdeal_status.setText(eatdealInfo.getStatus());
                discount_eatdeal_status.setTextColor(itemView.getResources().getColor(R.color.white));
                discount_eatdeal_status_img.setImageResource(R.drawable.discount_label_shape_red);
            }
            else if(eatdealInfo.getStatus() != null && eatdealInfo.getStatus().equals("NEW"))
            {
                discount_eatdeal_status.setText(eatdealInfo.getStatus());
                discount_eatdeal_status.setTextColor(itemView.getResources().getColor(R.color.white));
                discount_eatdeal_status_img.setImageResource(R.drawable.discount_label_shape_orange);
            }

            discount_eatdeal_original_price.setText("₩ " + eatdealInfo.getOriginalPrice());
            discount_eatdeal_original_price.setPaintFlags(discount_eatdeal_original_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            discount_eatdeal_sale_price.setText("₩ " + eatdealInfo.getSalePrice());

            discount_eatdeal_title.setText(eatdealInfo.getTitle());
            discount_eatdeal_item.setText(eatdealInfo.getItem());
            discount_eatdeal_description.setText(eatdealInfo.getDescription());
        }
    }
}
