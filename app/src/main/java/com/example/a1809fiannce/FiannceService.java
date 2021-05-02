package com.example.a1809fiannce;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class FiannceService extends Service {

    public FiannceService() {

    }

    @Override
    public IBinder onBind(Intent intent) {

        return new MyBind();

    }

    public class MyBind extends Binder{

            public FiannceService MyService(){

                return FiannceService.this;

        }
    }






}