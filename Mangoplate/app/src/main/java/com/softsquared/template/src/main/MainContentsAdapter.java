package com.softsquared.template.src.main;

import com.softsquared.template.src.main.announcement.AnnouncementFragment;
import com.softsquared.template.src.main.discount.DiscountFragment;
import com.softsquared.template.src.main.mypage.MypageFragment;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainContentsAdapter extends FragmentStateAdapter {

    private int mTabCount;
    private ViewPager mViewPager;


    public MainContentsAdapter(@NonNull FragmentActivity fm, int mTabCount) {
        super(fm);
        this.mTabCount = mTabCount;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0) return SearchRestaurantFragment.newInstance();
        else if(position == 1) return DiscountFragment.newInstance();
        else if(position == 2) return AnnouncementFragment.newInstance();
        else return MypageFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return mTabCount;
    }
}
