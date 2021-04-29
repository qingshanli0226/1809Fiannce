package com.example.user.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.common.CommonConstant;
import com.example.framework.module.FrameArouter;
import com.example.user.exitlogin.ExitActivity;
import com.example.user.login.LoginActivity;
import com.example.user.register.RegisterActivity;

public class UserModule implements FrameArouter.IUserInterface {
    public static void init(){
        UserModule userModel = new UserModule();
        //第一种跳转 接口回调
//        FrameArouter.getInstance().registerUserInterface(userModel);
        //第二 arouter
        FrameArouter.getInstance().registerPath(CommonConstant.USER_REGISTER_PATH,RegisterActivity.class);
        FrameArouter.getInstance().registerPath(CommonConstant.USER_LOGIN_PATH,LoginActivity.class);
        FrameArouter.getInstance().registerPath(CommonConstant.USER_EXIT_PATH, ExitActivity.class);
    }
    //第一种
    @Override
    public void onGoLogin(Context context,Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);

        if(context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        } else{
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    @Override
    public void onGoRegister(Context context,Bundle bundle) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if(context instanceof Activity){
            intent.putExtra("param",bundle);

            context.startActivity(intent);
        } else{
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
