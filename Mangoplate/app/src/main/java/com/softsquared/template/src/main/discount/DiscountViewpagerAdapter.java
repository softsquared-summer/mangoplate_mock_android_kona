package com.softsquared.template.src.main.discount;

import android.view.View;

import com.softsquared.template.src.main.discount.eatdeal.EatdealFragment;
import com.softsquared.template.src.main.discount.mangopick.MangopickFragment;
import com.softsquared.template.src.main.discount.toplist.ToplistFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DiscountViewpagerAdapter extends FragmentPagerAdapter {
    private int mPageCount;

    public DiscountViewpagerAdapter(@NonNull FragmentManager fm, int pageCount) {
        super(fm, pageCount);
        this.mPageCount = pageCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                EatdealFragment eatdealFragment = new EatdealFragment();
                return eatdealFragment;

            case 1:
                MangopickFragment mangopickFragment = new MangopickFragment();
                return mangopickFragment;

            case 2:
                ToplistFragment toplistFragment = new ToplistFragment();
                return toplistFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
