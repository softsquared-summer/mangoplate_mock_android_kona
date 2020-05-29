package com.softsquared.template.src.main.discount.eatdeal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.template.R;
import com.softsquared.template.src.main.discount.DiscountFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class EatdealFragment extends Fragment {

    DiscountFragment discountFragment;
    EatdealFragment eatdealFragment;
    private TabLayout tabLayout;
    private Context context;
    private ViewPager viewPager;
    private ViewGroup viewGroup;
    private RecyclerView recyclerView;

    EatdealRecyclerAdapter eatdealRecyclerAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.discount_eatdeal_fragment, container, false);

        eatdealRecyclerAdapter = new EatdealRecyclerAdapter(eatdealFragment);
        return viewGroup;
    }
}
