package com.softsquared.template.src.main.announcement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.template.R;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AnnouncementFragment extends Fragment {

    private ViewGroup viewGroup;

    public static AnnouncementFragment newInstance()
    {
        return new AnnouncementFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.announcement_fragment, container, false);
        return  viewGroup;
    }
}
