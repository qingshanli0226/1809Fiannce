package com.example.a1809fiannce.main.Welcome;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.BarUtils;
import com.example.a1809fiannce.R;
import com.example.commom.FianceConstants;
import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.service.FiannceService;
import com.example.framework.manager.CacheManager;
import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    private PackageInfo pi = null;
    private TextView actGreetDown;
    private TextView actGreetVersionName;
    private RelativeLayout actGreetRl;
    private int versionCode = 0;
    private boolean switchDowm = false;
    private boolean switchVersions = false;
    private boolean switchData = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int i = msg.what;

            actGreetDown.setText("倒计时:" + i + " | 跳过");
            if (i <= 0) {
                switchDowm = true;
                start();
                handler.removeCallbacksAndMessages(null);
            } else {
                handler.sendEmptyMessageDelayed(--i, 1000);
            }
            super.handleMessage(msg);
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            FiannceService.FiannceBinder fiannceBinder = (FiannceService.FiannceBinder) iBinder;
            fiannceService = fiannceBinder.getFiannceService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private AlphaAnimation alphaAnimation;
    private VersionBean versionBean;
    private String AROUT_MAINACTIVITY = "/main/MainActivity";
    private Intent intent;
    FiannceService fiannceService;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        BarUtils.transparentStatusBar(this);

        actGreetDown = (TextView) findViewById(R.id.act_greet_down);
        actGreetVersionName = (TextView) findViewById(R.id.act_greet_versionName);
        actGreetRl = (RelativeLayout) findViewById(R.id.act_greet_rl);
        intent = new Intent(this, FiannceService.class);

        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }


    @Override
    protected void initData() {

        /**
         * 自动登录
         */
        String token = SpUtil.getString(this, FianceConstants.TOKEN_KEY);
        if (!(token == null || token.equals(""))) {
            startService(intent);
        }


        httpPresenter.getServerVersion();
        httpPresenter.getHomeData();

        alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        actGreetRl.startAnimation(alphaAnimation);


        /**
         * 获取对象
         */
        try {
            pi = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        actGreetVersionName.setText(getResources().getText(R.string.current_version) + getVersionName());

        countDown();
    }


    private void start() {
        if (switchData && switchDowm && switchVersions) {
            addAlertDialogBuilder();
        }
    }


    private void addAlertDialogBuilder() {
        if (versionCode > getVersionCode()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
            builder.setTitle(getResources().getString(R.string.download_the_latest_version));
            builder.setMessage(versionBean.getResult().getDesc());
            builder.setNegativeButton(getResources().getText(R.string.no), (dialogInterface, i) -> {
//                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                ARouter.getInstance().build(AROUT_MAINACTIVITY).withString("", "").navigation();

                FiannceArouter.getInstance().build(FianceConstants.MAIN_PATH).navigation();
                finish();
            });
            builder.setPositiveButton(getResources().getText(R.string.yes), (dialogInterface, i) -> {
//                ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.show();
                fiannceService.downloadApk(CacheManager.getInstance().getVersionBean().getResult().getApkUrl());
//                fiannceService.openAPK(FianceConstants.SD_DOWNLOAD);
                FiannceArouter.getInstance().build(FianceConstants.MAIN_PATH).navigation();
                finish();
            });
            builder.setCancelable(false);
            builder.show();

        } else {
            FiannceArouter.getInstance().build(FianceConstants.MAIN_PATH).navigation();
            finish();
        }

    }

    private int getVersionCode() {
        return pi.versionCode;
    }

    private String getVersionName() {
        return pi.versionName;
    }

    @Override
    public void onWelcomeData(VersionBean versionBean) {
        if (versionBean.getCode() == 200) {
            CacheManager.getInstance().setVersionBean(versionBean);

            this.versionBean = versionBean;
            versionCode = versionBean.getResult().getVersionCode();
            switchVersions = true;
            start();
        }
    }

    private void countDown() {
        handler.sendEmptyMessageDelayed(3, 0);

        actGreetDown.setOnClickListener(view -> {
            handler.sendEmptyMessageAtTime(0, 0);
        });
    }

    @Override
    public void onHomeData(HoemBean hoemBean) {
        CacheManager.getInstance().setHoemBean(hoemBean);
        switchData = true;
        start();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {
        loadingPage.showErrorView(error);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
        alphaAnimation.cancel();
        unbindService(serviceConnection);

    }


}