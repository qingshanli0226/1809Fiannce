package com.example.myfinancial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.framework.mannager.FiannceArote;

public class AppModule implements FiannceArote.IAppInterface {
    public static void init(){
        AppModule appModule=new AppModule();
        FiannceArote.getInstance().registerIAppInterface(appModule);
    }
    @Override
    public void openMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(bundle);
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
    public void onEvent(String event) {
        Log.d("AppModule", event);
    }
}
