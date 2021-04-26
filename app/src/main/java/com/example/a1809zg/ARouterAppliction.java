package com.example.a1809zg;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

public class ARouterAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("666", "onCreate: ");
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}
