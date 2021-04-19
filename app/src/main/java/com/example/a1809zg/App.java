package com.example.a1809zg;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("666", "onCreate: ");
    }
}
