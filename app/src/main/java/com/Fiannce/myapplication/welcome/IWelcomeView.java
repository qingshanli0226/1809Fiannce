package com.Fiannce.myapplication.welcome;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.AllMoneyBean;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;

public interface IWelcomeView  extends IBaseView {

    void onHomeData(HomeBean homeBean);
    void onVersionData(VersionBean versionBean);

}
