package com.example.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.framework.manager.CacheManager;
import com.example.net.model.VersionBean;

import java.io.File;

public class DownloadApkService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //进度条
    private int mProgress;
    //保存路径
    private String mSavePath;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        VersionBean versionBean = CacheManager.getInstance().getVersionBean();
        String path = versionBean.getResult().getApkUrl();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File file = Environment.getExternalStorageDirectory();
        }


        return super.onStartCommand(intent, flags, startId);
    }
}
