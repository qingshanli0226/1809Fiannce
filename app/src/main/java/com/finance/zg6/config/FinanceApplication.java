package com.finance.zg6.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class FinanceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);

    }
}
