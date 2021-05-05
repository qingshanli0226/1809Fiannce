package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.manager.FiannceArouter;
import com.example.user.login.LoginActivity;
import com.example.user.register.RegisterActivity;
import com.example.user.userinfo.UserInfoActivity;

public class UserModule implements FiannceArouter.IUserInterface {

    public static void init(){
        UserModule userModule = new UserModule();
        FiannceArouter.getInstance().registerIUserInterface(userModule);
    }

    @Override
    public void openLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        } {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void openRegisterActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        } {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void openUserInfoActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        if (context instanceof Activity){
            LogUtils.d("123");
            context.startActivity(intent);
        }else  {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            LogUtils.d("456");
            context.startActivity(intent);
        }
    }

    @Override
    public void openGetureActivity(Context context, Bundle bundle) {

    }
}
