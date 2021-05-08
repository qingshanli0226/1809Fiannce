package com.example.a1809fiannce.app;

import android.content.Context;
import android.os.Bundle;

import com.example.a1809fiannce.main.MainActivity;
import com.example.a1809fiannce.main.more.password.UnlockActivity;
import com.example.commom.FianceConstants;
import com.example.framework.manager.FiannceArouter;

public class AppModule {
    public static void init() {
        FiannceArouter.getInstance().registerActivityPath(FianceConstants.MAIN_PATH, MainActivity.class);
        FiannceArouter.getInstance().registerActivityPath(FianceConstants.UNLOCK_PATH, UnlockActivity.class);
    }
}
