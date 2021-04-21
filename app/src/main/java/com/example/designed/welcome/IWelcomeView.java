package com.example.designed.welcome;

import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.VersionBean;

public interface IWelcomeView  extends IBaseView {

    void onHomeData(HomeBean homeBean);
    void onVersionData(VersionBean versionBean);

}
