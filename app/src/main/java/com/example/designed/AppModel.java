package com.example.designed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.designed.welcome.WelcomeActivity;
import com.fiannce.bawei.framework.manager.FiannceArouter;

public class AppModel implements FiannceArouter.IAppInterface {

    public static void init(){
        AppModel appModel = new AppModel();
        FiannceArouter.getInstance().registerIAppInterface(appModel);
    }

    @Override
    public void openMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);

        if (context instanceof Activity){
            context.startActivity(intent);
        }{
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
}
