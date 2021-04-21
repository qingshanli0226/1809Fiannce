package com.finance.zg6.welcome;

import com.finance.framework.IBaseView;
import com.finance.net.bean.HomeBean;
import com.finance.net.bean.VersionBean;

public interface IWelcomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);
    void onVersionData(VersionBean versionBean);
}
