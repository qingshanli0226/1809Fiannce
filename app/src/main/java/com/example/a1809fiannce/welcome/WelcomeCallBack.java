package com.example.a1809fiannce.welcome;

import com.example.framwork.IView;
import com.example.network.model.HomeBean;
import com.example.network.model.UpdateBean;

public interface WelcomeCallBack extends IView {
    void HomeData(HomeBean homeBean);

    void UpdateData(UpdateBean updateBean);
}
