package com.example.a1809fiannce.main.Welcome;

import com.example.framework.IBaseView;
import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

public interface IWelcomeView extends IBaseView {
    void onWelcomeData(VersionBean versionBean);
    void onHomeData(HoemBean hoemBean);
}
