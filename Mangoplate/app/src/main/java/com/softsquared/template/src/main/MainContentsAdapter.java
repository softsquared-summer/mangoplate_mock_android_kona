package com.softsquared.template.src.main;

import com.softsquared.template.src.main.discount.DiscountFragment;
import com.softsquared.template.src.main.mypage.MypageFragment;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainContentsAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    private ViewPager mViewPager;


    public MainContentsAdapter(@NonNull FragmentManager fm, int mTabCount) {
        super(fm, mTabCount);
        this.mTabCount = mTabCount;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                SearchRestaurantFragment searchFragment = new SearchRestaurantFragment();
                return searchFragment;
            case 1:
                DiscountFragment discountFragment = new DiscountFragment();
                return discountFragment;
//            case 2:
//                NoticeFragment noticeFragment = new NoticeFragment();
//                return noticeFragment;
//
            case 3:
                MypageFragment mypageFragment = new MypageFragment();
                return mypageFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
