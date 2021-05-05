package com.example.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.manager.FiannceArouter;

public class PayModule implements FiannceArouter.IPayInterface {
    public static void init(){
        PayModule payModule = new PayModule();
        FiannceArouter.getInstance().registerIPayInterface(payModule);
    }

    @Override
    public void openPayActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PayActivity.class);
        if (context instanceof Activity){
            context.startActivity(intent);
        } {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
