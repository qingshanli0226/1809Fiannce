package com.fiannce.bawei.user;

import android.content.Context;
import android.os.Bundle;

import com.fiannce.bawei.framework.manager.FiannceArouter;

public class UserModule implements FiannceArouter.IUserInterface {

    public void init(){
        UserModule userModule = new UserModule();
        FiannceArouter.getInstance().registerIUserInterface(userModule);
        FiannceArouter.getInstance().registerActivityPath();
    }

    @Override
    public void openLoginAcivity(Context context, Bundle bundle) {

    }

    @Override
    public void openGetureActivity(Context context, Bundle bundle) {

    }
}
