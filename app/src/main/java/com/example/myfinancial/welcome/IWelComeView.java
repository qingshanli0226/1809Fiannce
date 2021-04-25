package com.example.myfinancial.welcome;

import com.example.framework.BaseView;
import com.example.net.bean.HomeBean;
import com.example.net.bean.VersionBean;

public interface IWelComeView extends BaseView {
   void initWelcome(HomeBean homeBean);
   void initVersion(VersionBean versionBean);
}
