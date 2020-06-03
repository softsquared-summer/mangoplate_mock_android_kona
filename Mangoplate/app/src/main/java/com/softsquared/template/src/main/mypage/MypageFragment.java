package com.softsquared.template.src.main.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.softsquared.template.R;
import com.softsquared.template.src.main.search_restaurant.SearchRestaurantFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.softsquared.template.src.ApplicationClass.sSharedPreferences;

public class MypageFragment extends Fragment {



    public static MypageFragment newInstance()
    {
        return new MypageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (ViewGroup)inflater.inflate(R.layout.mypage_layout, container, false);

        init(view);

        return view;
    }

    private void init(View view)
    {
        CircularImageView circularImageView = view.findViewById(R.id.mypage_profile);
        String profileUrl = sSharedPreferences.getString("profileUrl", "");
        Glide.with(view.getContext()).load(profileUrl).into(circularImageView);

        TextView myNameTextview = view.findViewById(R.id.profile_username);
        myNameTextview.setText(sSharedPreferences.getString("name", ""));



    }
}
