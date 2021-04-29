package com.example.myfinancial.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.mannager.FiannceArote;
import com.example.myfinancial.AppModule;
import com.example.pay.PayModel;
import com.example.user.UserModule;


public class FiannceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        FiannceArote.getInstance().init(this);
        PayModel.init();
        UserModule.init();
        AppModule.init();
    }
}
