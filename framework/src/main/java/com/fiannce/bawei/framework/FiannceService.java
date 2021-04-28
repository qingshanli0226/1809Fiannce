package com.fiannce.bawei.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.fiannce.bawei.framework.manager.FiannceUserManager;

public class FiannceService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FiannceUserManager.getInstance().setLogin(true);
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);


    }
}
