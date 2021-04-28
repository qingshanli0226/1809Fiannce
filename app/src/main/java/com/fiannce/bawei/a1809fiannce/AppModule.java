package com.fiannce.bawei.a1809fiannce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.bawei.common.FiannceConstants;
import com.fiannce.bawei.framework.manager.FiannceArouter;
import com.fiannce.bawei.user.LoginActivity;

public class AppModule implements FiannceArouter.IAppInterface {
    public static void init() {
        AppModule appModule = new AppModule();
        FiannceArouter.getInstance().registerIAppInterface(appModule);
        FiannceArouter.getInstance().registerActivityPath(FiannceConstants.Main_PATH,MainActivity.class);
    }

    @Override
    public void openMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (context instanceof Activity) {
            intent.putExtra("param", bundle);
            context.startActivity(intent);
        } {
            intent.putExtra("param", bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    @Override
    public void onEvent(String event) {
        Log.d("LQS", " EVENT = " + event);
    }
}
