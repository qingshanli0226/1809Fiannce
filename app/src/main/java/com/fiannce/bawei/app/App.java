package com.fiannce.bawei.app;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.manager.CacheConnectManager;
import com.fiannce.framework.model.FrameArouter;
import com.fiannce.net.model.NetModel;
import com.fiannce.user.model.UserModel;
import com.fiannce.user.service.AutoService;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.init(this);

        NetModel.init(this);
        UserModel.init();
        AppModule.init();
        FrameArouter.getInstance().init(this);

        CacheConnectManager.getInstance().init(this);
        startService(new Intent(this, AutoService.class));
    }
}
