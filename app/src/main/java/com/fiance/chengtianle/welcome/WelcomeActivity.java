package com.fiance.chengtianle.welcome;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiance.chengtianle.R;
import com.fiance.framework.AppVerSion;
import com.fiance.framework.BaseActivity;
import com.fiance.framework.CacheManager;
import com.fiance.net.mode.HomeBean;
import com.fiance.net.mode.VersionBean;
import com.fiance.user.AutoLoginService;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    private TextView contentTv;
    private android.widget.ProgressBar progressBar;
    private TextView coundDownTv;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private int newAppVerSionCode;
    private int currentAppVerSionCode;
    private int countDown = 3;
    private AutoLoginService.MyBinder myBinder;
    private String apkPath="http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk";

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        loadingPage.showSuccessView();
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        loadingPage.showSuccessView();
        newAppVerSionCode=versionBean.getResult().getVersionCode();
        versionFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();

    }

    @Override
    protected void initView() {

        Intent intent = new Intent(WelcomeActivity.this, AutoLoginService.class);
        ServiceConnection serviceConnection= new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder= (AutoLoginService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

        contentTv = findViewById(R.id.countDownTv);
        progressBar = findViewById(R.id.progressBar);
        coundDownTv = findViewById(R.id.countDownTv);



        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
        coundDownTv.setText(countDown + "秒");

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
//      loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0) {
                        coundDownTv.setText(countDown + "秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
                    } else {
                        coundDownTv.setText(countDown + "秒");
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
                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                      currentAppVerSionCode=AppVerSion.getVersionCode(WelcomeActivity.this);


                    if (currentAppVerSionCode<newAppVerSionCode){
                        //对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                        builder.setTitle("检测到新版本");
                        builder.setMessage("检测到新版本  是否下载安装");
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(WelcomeActivity.this, "正在下载", Toast.LENGTH_SHORT).show();
                                myBinder.myMethod(apkPath);
                                ARouter.getInstance().build("/main/MainActivity").navigation();
                                finish();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else{
                        ARouter.getInstance().build("/main/MainActivity").navigation();
                     finish();
                    }

                    break;
            }
        }
    };

    @Override
    public void showError(String error) {
//        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }
}