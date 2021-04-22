package com.example.fiannce.welcome;

import com.example.framework.IBaseView;
import com.example.net.mode.HomeBean;
import com.example.net.mode.VersionBean;

public interface IWelcomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);
    void onVersionData(VersionBean versionBean);
}
