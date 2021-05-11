package com.example.designed.app;

import android.app.Application;

import com.example.designed.AppModel;
import com.fiannce.bawei.framework.manager.FiannceConnectManager;
import com.fiannce.bawei.net.NetModel;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppModel.init();

        NetModel.init(this);
        FiannceConnectManager.getInstance().init(this);

        UMConfigure.init(this,"604856545fc49d4ed0fb0923"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0


// QQ设置
        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");


// 新浪微博设置
        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");

// 其他平台设置



    }
}
