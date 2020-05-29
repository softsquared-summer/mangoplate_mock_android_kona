package com.example.mangoplate;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class MainActivityPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    private ViewPager mViewPager;
    private MainActivityPagerAdapter mainActivityPagerAdapter;

    public MainActivityPagerAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm, tabCount);
        this.mTabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                SearchFragment searchFragment = new SearchFragment();
                return searchFragment;
            case 1:
                DiscountFragment discountFragment = new DiscountFragment();
                return discountFragment;
            case 2:
                NoticeFragment noticeFragment = new NoticeFragment();
                return noticeFragment;

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
