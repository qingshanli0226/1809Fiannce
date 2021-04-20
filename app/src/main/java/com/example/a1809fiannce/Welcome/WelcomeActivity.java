package com.example.a1809fiannce.Welcome;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.MainActivity;
import com.example.a1809fiannce.R;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeView{


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
            LogUtils.e(msg.what);
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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



    @Override
    protected void initView() {
        actGreetDown = (TextView) findViewById(R.id.act_greet_down);
        actGreetVersionName = (TextView) findViewById(R.id.act_greet_versionName);
        actGreetRl = (RelativeLayout) findViewById(R.id.act_greet_rl);
    }

    @Override
    protected void initPresenter() {
        httpPresenter= new WelcomePresenter(this);
        httpPresenter.getServerVersion();
        httpPresenter.getHomeData();
    }


    @Override
    protected void initData() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
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
        actGreetVersionName.setText("当前版本：" + getVersionName());

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
            builder.setTitle("下载最新版本");
            builder.setMessage("解决—些bug,优化网络请求!");
            builder.setNegativeButton("取消", (dialogInterface, i) -> {
//                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                ARouter.getInstance().build("/main/MainActivity").withString("","").navigation();
                finish();
            });
            builder.setPositiveButton("确定", (dialogInterface, i) -> {
                ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            });
            builder.show();
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
            versionCode = versionBean.getResult().getVersionCode();
            switchVersions = true;
            start();
        }
    }

    private void countDown() {
        handler.sendEmptyMessageDelayed(4, 0);

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
    public void hileLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}