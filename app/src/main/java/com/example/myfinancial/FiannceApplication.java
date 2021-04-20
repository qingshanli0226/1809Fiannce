package com.example.myfinancial;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


public class FiannceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
