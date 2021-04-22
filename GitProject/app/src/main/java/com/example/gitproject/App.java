package com.example.gitproject;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        //屏幕适配
        ScreenAdapterTools.init(this);
    }
}
