package com.fiance.chengtianle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fiance.framework.BaseActivity;
import com.fiance.framework.FiannceConnectManager;

import java.util.List;

public class ConnectActivity extends BaseActivity {
    private TextView connctStatusTv;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_connect;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        if (FiannceConnectManager.getInstance().isConnected()) {
            connctStatusTv.setText("系统已经连接网络");
        } else {
            connctStatusTv.setText("系统没有连接网络");
        }

    }

    @Override
    protected void initView() {
        connctStatusTv = findViewById(R.id.connectStatusTv);
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(),0);
            int versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,isApplicationUsed()+" 应用是否是用户正在操作", Toast.LENGTH_SHORT).show();

      /*  ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = activityManager.getRunningTasks(1);
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            ComponentName componentName = runningTaskInfoList.get(0).topActivity;
            if(componentName.getPackageName().equals(getPackageName())) {

            }
        }*/

    }

    //检查当前我们的应用是否用户正在使用

    private boolean isApplicationUsed() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        //Androi系统里所有的应用都被ActivityMananger管理，它可以获取所有的应用列表
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        //遍历应用列表，比较每个应用程序包名和我们应用的包名是否一致，如果一致就代表找到了我们的应用
        for(ActivityManager.RunningAppProcessInfo runningAppProcessInfo:runningAppProcessInfoList) {
            if (runningAppProcessInfo.processName.equals(getPackageName())) {
                //判断当前我们的应用是否是前台进程，如果是代表着用户正在操作我们的应用
                if (runningAppProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    @Override
    public void onConnected() {
        connctStatusTv.setText("系统从未连接变为已经连接网络");
    }

    @Override
    public void onDisconnected() {
        connctStatusTv.setText("系统从已连接变为未连接网络");
    }

}