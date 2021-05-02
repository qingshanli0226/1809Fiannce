package com.example.a1809fiannce;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.a1809fiannce.mian.MainModel;
import com.example.framwork.call.FiannceNetManager;
import com.example.framwork.call.FiannceUserManager;
import com.example.user.UserModel;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        UserModel.init();
        MainModel.init();
        FiannceNetManager.getInstance().init(this);
        Intent intent = new Intent(this,FiannceService.class);
        startService(intent);

    }
}
