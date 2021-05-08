package com.example.a1809fiannce.welcome;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.MainActivity;
import com.example.a1809fiannce.R;
import com.example.framework.BaseActivity;
import com.example.framework.FiannceService;

import com.example.framework.manager.CacheManager;
import com.example.net.mode.HomeBean;
import com.example.net.mode.VersionBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {
//    private TextView text;
    private Intent intent;
    private ProgressBar pro;
    private RelativeLayout re;
    private TextView time;
    private PackageManager packageManager;
    private int D_code;
    private TextView versiontext;
    private AlphaAnimation alphaAnimation;

    private boolean TIME=false;
    private boolean HOME=false;
    private boolean VERSION=false;

    private VersionBean version;
    private ServiceConnection serviceConnection;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if (TIME&&HOME&&VERSION){
                    if (D_code < version.getResult().getVersionCode()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);

                        builder.setTitle(getResources().getString(R.string.download));
                        builder.setMessage(getResources().getString(R.string.requests));

                        builder.setNegativeButton(getResources().getString(R.string.NO), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setPositiveButton(getResources().getString(R.string.YES), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                serviceConnection = new ServiceConnection() {
                                    @Override
                                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                                        FiannceService.FiannceBinder fiannceBinder = (FiannceService.FiannceBinder) iBinder;

                                        FiannceService fiannceService = fiannceBinder.getFiannceService();

                                        fiannceService.DownLoad(version.getResult().getApkUrl());

                                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onServiceDisconnected(ComponentName componentName) {

                                    }
                                };

                                bindService(intent, serviceConnection,BIND_AUTO_CREATE);

//                                ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
//
//                                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//
//                                progressDialog.setCanceledOnTouchOutside(false);
//
//                                progressDialog.show();
                            }
                        });

                        builder.show();
                    }else {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }
    };

    @Override
    public void onHomeData(HomeBean homeBean) {
        HOME=true;

        CacheManager.getInstance().setHomeBean(homeBean);

        handler.sendEmptyMessage(1);

//        text.setText(homeBean.toString());
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        version=versionBean;

        VERSION=true;

        handler.sendEmptyMessage(1);

//        Toast.makeText(this, getResources().getString(R.string.VersionInformation)+versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        versiontext = (TextView) findViewById(R.id.versiontext);
        time = (TextView) findViewById(R.id.time);
        re = (RelativeLayout) findViewById(R.id.re);
//        text = (TextView) findViewById(R.id.text);
        pro = (ProgressBar) findViewById(R.id.pro);
    }

    @Override
    protected void initData() {

        requestPermissions(new String[]{"android.permission.CALL_PHONE"
        ,"android.permission.WRITE_EXTERNAL_STORAGE"
        ,"android.permission.READ_EXTERNAL_STORAGE"
        ,"android.permission.SYSTEM_ALERT_WINDOW"},100);

        httpPresenter.getHomeData();
        httpPresenter.getVersionData();

        packageManager = getPackageManager();

        intent = new Intent(this, FiannceService.class);
        startService(intent);

        D_code = getCode();

        versiontext.setText(getResources().getString(R.string.version)+D_code+".0");

        alphaAnimation = new AlphaAnimation(0f, 1f);

        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(2000);

        re.startAnimation(alphaAnimation);

        getTime();
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new WelcomePresenter(this);
    }

    @Override
    public void showLoading() {
//        pro.setVisibility(View.VISIBLE);
//        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
//        pro.setVisibility(View.GONE);
//        loadingPage.showSuccessView();
    }

    @Override
    public void Error(String error) {
        LogUtils.d(error);
        Toast.makeText(this, getResources().getString(R.string.RequestError)+error, Toast.LENGTH_SHORT).show();

//        loadingPage.showError(error);
    }

    public void getTime() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int count = 3;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(getResources().getString(R.string.CountDown) + count);

                        count--;

                        if (count < 0) {
                            TIME=true;

                            handler.sendEmptyMessage(1);
                            
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private String getName() {
        try {
            String versionName = packageManager.getPackageInfo(getPackageName(), 0).versionName;

            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getCode() {
        try {
            int versionCode = packageManager.getPackageInfo(getPackageName(), 0).versionCode;

            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void destroy() {
        super.destroy();
        alphaAnimation.cancel();
        handler.removeCallbacksAndMessages(null);

        if (serviceConnection!=null){
            unbindService(serviceConnection);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}