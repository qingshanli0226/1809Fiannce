package com.example.fiannce.fragment;

import com.example.framework.IBaseView;
import com.example.net.mode.AllBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.RegBean;
import com.example.net.mode.UpdateBean;

public interface BeanBack extends IBaseView {

    void HomeData(HomeBean homeBean);

    void UpdateData(UpdateBean updateBean);

    void AllData(AllBean allBean);

    void RegData(RegBean regBean);
}
