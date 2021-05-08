package com.example.gitproject;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.module.FrameArouter;
import com.example.gitproject.module.AppModule;
import com.example.net.module.NetModule;
import com.example.user.module.UserModule;

import leakcanary.LeakCanary;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        //net传入上下文
        NetModule.init(this);

        //user模块初始
        UserModule.init();
        //app
        AppModule.init();


        //自定义arouter
        FrameArouter.getInstance().init(this);

        CacheConnectManager.getInstance().init(this);



    }
}
