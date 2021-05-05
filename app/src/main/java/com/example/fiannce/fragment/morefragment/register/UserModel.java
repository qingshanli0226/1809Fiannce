package com.example.fiannce.fragment.morefragment.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fiannce.fragment.morefragment.login.LoginActivity;
import com.example.framework.FiannceARouter;

public class UserModel implements FiannceARouter.iUserManager {

    public static void init(){
        UserModel userModel = new UserModel();
        FiannceARouter.getFiannceARouter().setUserManager(userModel);
    }

    @Override
    public void OpenLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void OpenRegisterActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context,RegisterActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
