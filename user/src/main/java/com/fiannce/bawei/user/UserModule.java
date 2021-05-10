package com.fiannce.bawei.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.common.FiannceConstants;
import com.fiannce.bawei.framework.manager.FiannceArouter;

import com.fiannce.bawei.user.login.LoginActivity;
import com.fiannce.bawei.user.regiseter.RegisterActivity;

public class UserModule implements FiannceArouter.IUserInterface {

    public void initLogin(){
        UserModule userModule = new UserModule();
        FiannceArouter.getInstance().registerIUserInterface(userModule);
        FiannceArouter.getInstance().registerActivityPath(FiannceConstants.USER_LOGIN_PATH, LoginActivity.class);
    }
    public void initRegister(){
        UserModule userModule = new UserModule();
        FiannceArouter.getInstance().registerIUserInterface(userModule);
        FiannceArouter.getInstance().registerActivityPath(FiannceConstants.USER_REGISTER_PATH, LoginActivity.class);
    }

    @Override
    public void openLoginAcivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else{
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void openGetureActivity(Context context, Bundle bundle) {

    }

    @Override
    public void openRegisterActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context,RegisterActivity.class);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else{
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
