package com.example.a1809fiannce.app;

import android.app.Application;

import com.blankj.utilcode.util.ScreenUtils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);
    }
}
