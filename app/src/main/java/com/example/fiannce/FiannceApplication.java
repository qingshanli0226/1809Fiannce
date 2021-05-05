package com.example.fiannce;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.fiannce.fragment.morefragment.register.MainModel;
import com.example.fiannce.fragment.morefragment.register.UserModel;
import com.example.framework.FiannceService;
import com.example.framework.manager.FiannceNetManager;

public class FiannceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);

        UserModel.init();
        MainModel.init();

        FiannceNetManager.getInstance().init(this);

        Intent intent = new Intent(this, FiannceService.class);
        startService(intent);
    }
}
