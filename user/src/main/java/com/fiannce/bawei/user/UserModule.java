package com.fiannce.bawei.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fiannce.bawei.common.FiannceConstants;
import com.fiannce.bawei.framework.manager.FiannceArouter;

public class UserModule implements FiannceArouter.IUserInterface {

    public static void init() {
        UserModule userModule = new UserModule();
        FiannceArouter.getInstance().registerIUserInterface(userModule);
        FiannceArouter.getInstance().registerActivityPath(FiannceConstants.LOGIN_PATH,LoginActivity.class);
    }

    @Override
    public void openLoginAcivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
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
    public void openGetureActivity(Context context, Bundle bundle) {

    }
}
