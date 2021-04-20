package com.example.a1809fiannce.Welcome;

import com.example.framework.IBaseView;
import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

public interface WelcomeView extends IBaseView {
    void onWelcomeData(VersionBean versionBean);
    void onHomeData(HoemBean hoemBean);
}
