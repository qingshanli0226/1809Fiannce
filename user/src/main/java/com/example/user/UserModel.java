package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.FiannceARouter;
import com.example.user.user.UserActivity;

public class UserModel implements FiannceARouter.iUserManager {

    public static void init(){
        UserModel userModel = new UserModel();
        FiannceARouter.getFiannceARouter().setUserManager(userModel);
    }

    @Override
    public void OpenUserActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UserActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}