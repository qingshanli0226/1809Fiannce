package com.example.a1809fiannce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.manager.FiannceArouter;

public class AppModule implements FiannceArouter.IAppInterface {
    public static void init(){
        AppModule appModule = new AppModule();
        FiannceArouter.getInstance().registerIAppInterface(appModule);
    }

    @Override
    public void openMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context,MainActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        } {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}