package com.example.a1809fiannce.app;

import android.app.Application;
import android.content.Intent;

import com.blankj.utilcode.util.ScreenUtils;
import com.example.a1809fiannce.AppModule;
import com.example.framework.LoginService;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceConnectManager;
import com.example.net.NetModel;
import com.example.pay.PayModule;
import com.example.user.UserModule;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);

//        Intent intent = new Intent(this, LoginService.class);
//        startService(intent);

        NetModel.init(this);

        FiannceArouter.getInstance().init(this);
        AppModule.init();
        UserModule.init();
        PayModule.init();

        FiannceConnectManager.getInstance().init(this);
    }
}
