package com.example.designed.app;

import android.app.Application;

import com.example.designed.AppModel;
import com.fiannce.bawei.framework.manager.FiannceConnectManager;
import com.fiannce.bawei.net.NetModel;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppModel.init();

        NetModel.init(this);
        FiannceConnectManager.getInstance().init(this);
    }
}
