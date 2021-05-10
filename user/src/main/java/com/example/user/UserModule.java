package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.mannager.FiannceArote;

public class UserModule implements FiannceArote.IUserInterface {
    public static void init(){
        UserModule userModule=new UserModule();
        FiannceArote.getInstance().registerIUserInterface(userModule);
    }
    @Override
    public void openLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LogActivity.class);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void openGettureActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void openUserMessageActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UserMessageActivity.class);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
