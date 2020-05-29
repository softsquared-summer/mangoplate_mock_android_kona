package com.softsquared.template.src.main.search_restaurant;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.template.R;
import com.softsquared.template.src.main.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class SearchRestaurantFragment extends Fragment {

    MainActivity mainActivity;
    private ViewGroup mViewGroup;
    private ViewPager mViewPager;
    Context mContext;
    SearchRestaurantTopAdAdapter searchRestaurantTopAdAdapter;

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

        searchRestaurantTopAdAdapter = new SearchRestaurantTopAdAdapter(getContext());

        mViewPager = mViewGroup.findViewById(R.id.ad_viewPager);
        mViewPager.setClipToPadding(false);

        mViewPager.setAdapter(searchRestaurantTopAdAdapter);

        return mViewGroup;
    }
}
