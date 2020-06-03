package com.softsquared.template.src.main.discount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.template.R;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class DiscountFragment extends Fragment {

    private ViewGroup viewGroup;
    DiscountViewpagerAdapter discountViewpagerAdapter;


    public static DiscountFragment newInstance()
    {
        return new DiscountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.discount_layout, container, false);

        discountView(viewGroup);

        return viewGroup;
    }

    private void discountView(View view)
    {
        TabLayout tabLayout = view.findViewById(R.id.discount_tablayout);
        final ViewPager viewPager = view.findViewById(R.id.discount_viewpager);

        discountViewpagerAdapter = new DiscountViewpagerAdapter(getFragmentManager(), 3);
        viewPager.setAdapter(discountViewpagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
