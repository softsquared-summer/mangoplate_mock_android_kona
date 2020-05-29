package com.softsquared.template.src.main.discount.eatdeal.interfaces;

import com.softsquared.template.src.main.discount.eatdeal.models.EatdealInfo;
import com.softsquared.template.src.main.models.UserInfo;

public interface EatdealActivityView {

    void onSuccessGetEatdeal(EatdealInfo eatdealInfo);

    void onFailureGetEatdeal();
}
