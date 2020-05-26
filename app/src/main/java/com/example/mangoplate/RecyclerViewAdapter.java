package com.example.mangoplate;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<Integer> list;

    public RecyclerViewAdapter(Context mContext, ArrayList<Integer> list){
        super();
        this.mContext = mContext;
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView res_img;
        TextView res_name;
        TextView res_location;
        TextView res_views;
        TextView res_update;
        TextView res_rate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            res_img = itemView.findViewById(R.id.restaurant_img);
            res_name = itemView.findViewById(R.id.restaurant_name);
            res_location = itemView.findViewById(R.id.restaurant_distance);
            res_views = itemView.findViewById(R.id.restaurant_views);
            res_update = itemView.findViewById(R.id.restaurant_update);
            res_rate = itemView.findViewById(R.id.restaurant_rate);
        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_gridlayout ,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
//        holder.res_img.setImageResource(list.get(position).res_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
