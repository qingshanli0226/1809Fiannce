package com.fiannce.myapplication.welcome;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;


import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.framework.view.LoadingPage;
import com.fiannce.myapplication.MainActivity;
import com.fiannce.myapplication.R;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;
import com.fiannce.user.autologin.AutoLoginService;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private int countDown = 3;
    private ServiceConnection serviceConnection;
    private AutoLoginService autoLoginService;
    private Intent intent;
    private int code;
    private VersionBean versionBean;

    @Override
    protected void initView() {
        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
    }

    @Override
    protected void initPresenter() {
        httppresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        requestPermissions(new String[]{"android.permission.CALL_PHONE"
                , "android.permission.WRITE_EXTERNAL_STORAGE"
                , "android.permission.READ_EXTERNAL_STORAGE"
                , "android.permission.SYSTEM_ALERT_WINDOW"}, 100);

        httppresenter.getHomeData();
        httppresenter.getVersionData();

        intent = new Intent(this, AutoLoginService.class);
        startService(intent);
        code = getLocalVersion(this);
    }

    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            Log.i("TAG", "当前版本号：" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);

    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        this.versionBean = versionBean;
        versionFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "请求出现错误：" + error, Toast.LENGTH_SHORT);
        loadingPage.showError(error);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0) {
                        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
                    } else {
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;
                case ONE_TASK_FIISH:
                    if (homeFinsh && versionFinsh && advertistFinsh) {
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;
                case ALL_TASK_FIISH:
                    if (code < versionBean.getResult().getVersionCode()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                        builder.setTitle("下载最新版本");
                        builder.setMessage("解决一些bug,优化网络请求");
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                                ARouter.getInstance().build("/main/MainActivity").withInt("", 1).navigation();
                                finish();
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                serviceConnection = new ServiceConnection() {
                                    @Override
                                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                                        AutoLoginService.MyBinder myBinder = (AutoLoginService.MyBinder) iBinder;
                                        AutoLoginService autoLoginService = myBinder.getAutoLoginService();
                                        Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                                        ARouter.getInstance().build("/main/MainActivity").withInt("", 1).navigation();
                                        finish();
                                    }

                                    @Override
                                    public void onServiceDisconnected(ComponentName componentName) {

                                    }
                                };

                                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                            }
                        });
                        builder.show();
                    } else {
                        Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                        ARouter.getInstance().build("/main/MainActivity").withInt("", 1).navigation();
                        finish();
                    }

                    break;
            }
        }
    };

    @Override
    public void destory() {
        super.destory();
        handler.removeCallbacksAndMessages(null);
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}
