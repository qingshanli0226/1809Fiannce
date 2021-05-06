package com.example.a1809zg;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.frame.CacheUserManager;
import com.example.frame.CacheconnetManager;
import com.example.frame.FrameArouter;
import com.example.net.NetMoudel;
import com.example.user.UserMoudel;

public class ARouterAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("666", "onCreate: ");
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        NetMoudel.init(this);
        FrameArouter.getInstance().init(this);
        CacheconnetManager.getInstance().init(this);
        UserMoudel.init();
    }
}
