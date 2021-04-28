package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.commom.FianceConstants;
import com.example.framework.manager.FiannceArouter;
import com.example.user.register.RegisterActivity;

public class UserModule implements FiannceArouter.IUsetInterface {
    public static void init(){
        UserModule userModule = new UserModule();
//        FiannceArouter.getInstance().registerUsetInterface(userModule);
        FiannceArouter.getInstance().registerActivityPath(FianceConstants.REGISTER_PATH,RegisterActivity.class);
    }

    @Override
    public void openLoginActivity(Context context, Bundle bundle) {


    }

    @Override
    public void openReginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
