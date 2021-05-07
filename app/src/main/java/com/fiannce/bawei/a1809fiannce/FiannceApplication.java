package com.fiannce.bawei.a1809fiannce;

import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.bawei.framework.FiannceService;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.framework.manager.FiannceArouter;
import com.fiannce.bawei.framework.manager.FiannceConnectManager;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.net.NetModule;
import com.fiannce.bawei.pay.PayModule;
import com.fiannce.bawei.user.UserModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class FiannceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);

        FiannceArouter.getInstance().init(this);
        FiannceConnectManager.getInstance().init(this);
        NetModule.init(this);
        UserModule.init();
        PayModule.init();
        AppModule.init();
        Intent intent = new Intent(this,FiannceService.class);
        startService(intent);

        //使用LeakCannary来检查Activity会不会出现内存泄漏问题
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }

        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
