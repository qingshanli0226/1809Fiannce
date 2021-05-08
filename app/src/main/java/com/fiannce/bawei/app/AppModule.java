package com.fiannce.bawei.app;

import android.content.Context;

import com.fiannce.bawei.MainActivity;
import com.fiannce.commond.CommonConstant;
import com.fiannce.framework.model.FrameArouter;

public class AppModule {
    public static void init() {
        FrameArouter.getInstance().registerPath(CommonConstant.APP_MAIN_PATH, MainActivity.class);
    }
}
