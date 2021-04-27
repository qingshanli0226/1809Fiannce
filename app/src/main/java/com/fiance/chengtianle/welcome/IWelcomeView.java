package com.fiance.chengtianle.welcome;

import com.fiance.framework.IBaseView;
import com.fiance.net.mode.HomeBean;
import com.fiance.net.mode.VersionBean;

public interface IWelcomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);

    void onVersionData(VersionBean versionBean);


}
