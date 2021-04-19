package com.example.a1809fiannce;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}
