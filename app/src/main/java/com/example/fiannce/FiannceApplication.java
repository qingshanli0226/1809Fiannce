package com.example.fiannce;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.Squilts;
import com.example.fiannce.fragment.morefragment.MainModel;
import com.example.user.UserModel;
import com.example.framework.FiannceService;
import com.example.framework.manager.FiannceNetManager;
import com.example.user.self.SelfService;

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

        if (Squilts.getString(this)!= null){
            Intent intent1 = new Intent(this, SelfService.class);
            startActivity(intent1);
        }
    }
}
