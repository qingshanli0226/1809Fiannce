package com.example.framwork;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.framwork.call.FiannceUserManager;

import java.util.List;

public class FiannceService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //FiannceUserManager.getInstance().setLog(true);
                handler.sendEmptyMessage(1);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            isApplicationUsed();
        }
    };

    private boolean isApplicationUsed() {

        ActivityManager activityManager= (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if (runningAppProcess.processName.equals(getPackageName())){
                if (runningAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){

                    Log.i("zx", "在当前应用: ");
                    return true;

                }else {

                    Log.i("zx", "不在当前应用: ");
                    return false;

                }
            }
        }

        return false;

    }
}
