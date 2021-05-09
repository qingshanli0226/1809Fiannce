package com.example.a1809fiannce;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.a1809fiannce.main.MainModel;
import com.example.common.ComContext;
import com.example.framwork.FiannceService;
import com.example.framwork.call.FiannceNetManager;
import com.example.network.retrofit.Head;
import com.example.user.auto.AutoService;
import com.example.common.Squilts;
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


        Intent connectService = new Intent(this, FiannceService.class);
        startService(connectService);

        if (Squilts.getString(this)!=null){
            Log.i("zx", "onCreate: "+Squilts.getString(this));
            Intent autoService = new Intent(this, AutoService.class);
            startService(autoService);
        }

        ComContext.getInstance().init(this);


    }
}
