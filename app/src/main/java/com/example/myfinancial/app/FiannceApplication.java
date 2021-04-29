package com.example.myfinancial.app;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.mannager.FiannceArote;
import com.example.framework.mannager.FiannceConnectMannager;
import com.example.myfinancial.AppModule;
import com.example.pay.PayModel;
import com.example.user.UserModule;
import com.example.user.service.AutoLoginService;


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


        FiannceConnectMannager.getInstance().init(this);

        startService(new Intent(this,AutoLoginService.class));
    }
}
