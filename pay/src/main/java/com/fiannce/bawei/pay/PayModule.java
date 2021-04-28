package com.fiannce.bawei.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fiannce.bawei.common.FiannceConstants;
import com.fiannce.bawei.framework.manager.FiannceArouter;

public class PayModule implements FiannceArouter.IPayInterface {

    public static void init() {
        PayModule payModule = new PayModule();
        FiannceArouter.getInstance().registerIPayInterface(payModule);
        FiannceArouter.getInstance().registerActivityPath(FiannceConstants.PAY_PATH,PayActivity.class);

    }


    @Override
    public void openPayActivity(Context context, Bundle bundle) {

        Intent intent = new Intent(context, PayActivity.class);
        if (context instanceof Activity) {
            intent.putExtra("param", bundle);
            context.startActivity(intent);
        } {
            intent.putExtra("param", bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
