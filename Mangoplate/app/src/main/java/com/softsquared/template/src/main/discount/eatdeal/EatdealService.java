package com.softsquared.template.src.main.discount.eatdeal;

import android.util.Log;

import com.softsquared.template.R;
import com.softsquared.template.src.main.MainActivity;
import com.softsquared.template.src.main.discount.eatdeal.interfaces.EatdealRetrofitInterface;
import com.softsquared.template.src.main.discount.eatdeal.models.EatdealInfo;
import com.softsquared.template.src.main.discount.eatdeal.models.EatdealResponse;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.recyclerview.widget.RecyclerView;

import static com.softsquared.template.src.ApplicationClass.TAG;
import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class EatdealService {
    private MainActivity mainActivity;

    private EatdealRecyclerAdapter eatdealRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    EatdealInfo eatdealInfo = new EatdealInfo();
    EatdealService(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    void tryGetEatdeal()
    {
        final EatdealRetrofitInterface eatdealRetrofitInterface = getRetrofit().create(EatdealRetrofitInterface.class);
        Log.e("성공", "성공");
        eatdealRetrofitInterface.toString();

        eatdealRetrofitInterface.getEatdeal(X_ACCESS_TOKEN).enqueue(new Callback<EatdealResponse>() {
            @Override
            public void onResponse(Call<EatdealResponse> call, Response<EatdealResponse> response) {
                final EatdealResponse eatdealResponse = response.body();
                init();
                if (eatdealResponse.getResult() != null && eatdealResponse.getResult().size() > 0)
                {
                    for(EatdealInfo eatdealInfo : eatdealResponse.getResult())
                    {
                        if(response.code() == 200)
                        {
                            if(eatdealResponse.getResult() != null)
                            {
                                Log.e("url", "" + eatdealInfo.getImageUrl());
                                eatdealRecyclerAdapter.add(eatdealInfo);
                            }
                        }
                    }
                }
                eatdealRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<EatdealResponse> call, Throwable t) {

            }
        });
    }

    private void init()
    {
        recyclerView = mainActivity.findViewById(R.id.eatdeal_recyclerview);
        gridLayoutManager = new GridLayoutManager(mainActivity, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        eatdealRecyclerAdapter = new EatdealRecyclerAdapter(mainActivity);
        recyclerView.setAdapter(eatdealRecyclerAdapter);
        Log.e("어댑터 장착 완료", "어댑");
    }




}
