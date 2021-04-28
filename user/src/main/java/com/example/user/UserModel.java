package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framwork.call.FiannceARouter;
import com.example.user.reg.RegisterActivity;

public class UserModel implements FiannceARouter.iUserManager {

    public static void init(){
        UserModel userModel = new UserModel();
        FiannceARouter.getFiannceARouter().setUserManager(userModel);
    }

    @Override
    public void OpenLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LogActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void OpenRegisterActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
