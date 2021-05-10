package com.example.gesture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.mannager.FiannceArote;

public class GestureModule implements FiannceArote.IGestureInterface {
    public static void init(){
        GestureModule userModule=new GestureModule();
        FiannceArote.getInstance().registerIGestureInterface(userModule);
    }
    @Override
    public void openGestureActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GestureActivity.class);
        if (context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
//
//    @Override
//    public void openGettureActivity(Context context, Bundle bundle) {
//        Intent intent = new Intent(context, RegisterActivity.class);
//        if (context instanceof Activity){
//            intent.putExtra("param",bundle);
//            context.startActivity(intent);
//        }else {
//            intent.putExtra("param",bundle);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//        }
//    }
}
