package com.fiance.chengtianle;

import android.app.ActivityManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiance.chengtianle.Adapter.MyVp;
import com.fiance.chengtianle.Fragment.MainFragment;

import com.fiance.chengtianle.Fragment.InvestorFragment;
import com.fiance.chengtianle.Fragment.MineFragment;
import com.fiance.chengtianle.Fragment.MoreFragment;
import com.fiance.framework.BaseActivity;
import com.fiance.framework.FiannceConnectManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {


    private TabLayout tab;
    private ViewPager vp;


    @Override
    protected void initView() {
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);

        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(),0);
            int versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        if (FiannceConnectManager.getInstance().isConnected()) {
            Toast.makeText(this, "系统已经连接网络", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "系统没有连接网络", Toast.LENGTH_SHORT).show();
        }

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new MainFragment());
        list.add(new InvestorFragment());
        list.add(new MineFragment());
        list.add(new MoreFragment());
        ArrayList<String> title = new ArrayList<>();
        title.add("首页");
        title.add("投资");
        title.add("我的资产");
        title.add("更多");

        MyVp myVp = new MyVp(getSupportFragmentManager(), list, title);
        vp.setAdapter(myVp);
        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setIcon(R.drawable.bottom02);
        tab.getTabAt(1).setIcon(R.drawable.qb);
        tab.getTabAt(2).setIcon(R.drawable.bottom06);
        tab.getTabAt(3).setIcon(R.drawable.bottom08);

    }
    private boolean isApplicationUsed() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo runningAppProcessInfo:runningAppProcessInfoList) {
            if (runningAppProcessInfo.processName.equals(getPackageName())) {
                if (runningAppProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onConnected() {
        Toast.makeText(this, "系统从未连接变为已经连接网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
       Toast.makeText(this, "系统从已连接变为未连接网络", Toast.LENGTH_SHORT).show();
    }


}