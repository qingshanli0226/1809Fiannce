package com.example.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.framework.mannager.FiannceArote;

public class PayModel implements FiannceArote.IPayInterface {
    public static void init(){
        PayModel payModel=new PayModel();
        FiannceArote.getInstance().registerIPayInterface(payModel);
    }
    @Override
    public void openPayActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PayActivity.class);
        if (context instanceof Activity){
            intent.putExtra("params",bundle);
            context.startActivity(intent);
        }else {
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
