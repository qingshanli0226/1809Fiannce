package com.example.a1809fiannce.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.NetModule;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);

        ScreenAdapterTools.init(this);

        NetModule.init(this);
    }
}
