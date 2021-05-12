package com.example.myapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.FrameModel;
import com.example.framework.manager.FiannceConnectManager;
import com.example.net.NetModel;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        FiannceConnectManager.getInstance().init(this);
        NetModel.init(this);
        FrameModel.init(this);


    }
}
