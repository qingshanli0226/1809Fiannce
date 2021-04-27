package com.example.gitproject;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.model.NetConstant;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        NetConstant.init(this);

    }
}
