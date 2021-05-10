package com.example.a1809fiannce.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.manager.FianceManager;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.service.FiannceService;
import com.example.net.NetModule;
import com.example.user.UserModule;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);

        ScreenAdapterTools.init(this);

        NetModule.init(this);

        UserModule.init();

        FiannceArouter.getInstance().init(this);

        AppModule.init();

        FianceManager.getInstance().init(this);

    }
}
