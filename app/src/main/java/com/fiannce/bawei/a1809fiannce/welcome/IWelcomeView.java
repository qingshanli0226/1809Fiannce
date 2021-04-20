package com.fiannce.bawei.a1809fiannce.welcome;

import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.VersionBean;

public interface IWelcomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);
    void onVersionData(VersionBean versionBean);
}
