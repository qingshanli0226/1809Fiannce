package com.example.gitproject.module;

import com.example.common.CommonConstant;
import com.example.framework.module.FrameArouter;
import com.example.gitproject.MainActivity;

public class AppModule {
    public static void init(){
        FrameArouter.getInstance().registerPath(CommonConstant.APP_MAIN_PATH,MainActivity.class);
    }
}
