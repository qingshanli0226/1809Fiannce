package com.example.user;

import android.content.Context;
import android.os.Bundle;

import com.example.frame.CommonConstant;
import com.example.frame.FrameArouter;
import com.example.user.login.LoginActivity;
import com.example.user.register.RegisterActivity;


public class UserMoudel implements FrameArouter.IuserInterface {
   public static void init(){
       UserMoudel userModel = new UserMoudel();

       //第二 arouter
       FrameArouter.getInstance().registerpath(CommonConstant.USER_REGISTER_PATH, RegisterActivity.class);
       FrameArouter.getInstance().registerpath(CommonConstant.USER_LOGIN_PATH, LoginActivity.class);

   }

    @Override
    public void onGologin(Context context, Bundle bundle) {

    }

    @Override
    public void onGoregister(Context context, Bundle bundle) {

    }
}
