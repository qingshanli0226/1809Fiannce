package com.example.myapplication.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.manager.FinanceARouter;
import com.example.myapplication.fragment.more.gesturepassword.GesturePasswordActivity;
import com.example.myapplication.fragment.more.loginbygesturepassword.LoginByGestPasswordActivity;

public class MainModel implements FinanceARouter.IAppManger {

    public static void init(){
        MainModel mainModel = new MainModel();
        FinanceARouter.getInstance().setAppManger(mainModel);
    }

    @Override
    public void openMainActivity(Context context, Bundle bundle) {

    }

    @Override
    public void openGesturePassword(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginByGestPasswordActivity.class);

        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
