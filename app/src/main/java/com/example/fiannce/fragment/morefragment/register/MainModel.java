package com.example.fiannce.fragment.morefragment.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fiannce.MainActivity;
import com.example.framework.FiannceARouter;

public class MainModel implements FiannceARouter.iAppManager {

    public static void init(){
        MainModel mainModel = new MainModel();
        FiannceARouter.getFiannceARouter().setAppManager(mainModel);
    }

    @Override
    public void OpenMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);

        int num = bundle.getInt("num");
        String name = bundle.getString("name");

        intent.putExtra("num",num);
        intent.putExtra("name",name);

        if (context instanceof Activity){
            context.startActivity(intent);
        }else {
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
}
