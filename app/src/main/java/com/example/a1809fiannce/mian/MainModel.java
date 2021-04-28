package com.example.a1809fiannce.mian;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framwork.call.FiannceARouter;

public class MainModel implements FiannceARouter.iAppManager {
    public static void init(){
        MainModel mainModel = new MainModel();
        FiannceARouter.getFiannceARouter().setAppManager(mainModel);
    }
    @Override
    public void OpenMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity2.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
