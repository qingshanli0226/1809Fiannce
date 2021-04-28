package com.fiannce.bawei.a1809fiannce;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.bawei.framework.manager.FiannceArouter;
import com.fiannce.bawei.net.NetModule;
import com.fiannce.bawei.pay.PayModule;
import com.fiannce.bawei.user.UserModule;

public class FiannceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);

        FiannceArouter.getInstance().init(this);
        NetModule.init(this);
        UserModule.init();
        PayModule.init();
        AppModule.init();

    }
}
