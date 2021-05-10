package com.example.a1809fiannce.welcome;

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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.alibaba.android.arouter.launcher.ARouter;

import com.example.a1809fiannce.MainActivity;
import com.example.a1809fiannce.R;
import com.example.common.FiannceConstants;
import com.example.common.SpUtil;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.framework.manager.FiannceArouter;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.ProductBean;
import com.fiannce.bawei.net.mode.VersionBean;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.user.service.AutoService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView, FiannceUserManager.IUserLoginChanged {
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1*1000;//1秒
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private int countDown = 3;
    private android.widget.ImageView icon;
    private AlphaAnimation alphaAnimation;
    private TextView correntTv;
    private AlertDialog.Builder alerDialog;
    private int code;
    private Intent intent;
    private VersionBean versionBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
        FiannceUserManager.getInstance().register(this);

    }

    @Override
    protected void initData() {
        requestPermissions(new String[]{"android.permission.CALL_PHONE"
                , "android.permission.WRITE_EXTERNAL_STORAGE"
                , "android.permission.READ_EXTERNAL_STORAGE"
                , "android.permission.SYSTEM_ALERT_WINDOW"}, 100);

        httpPresenter.getHomeData();
        httpPresenter.getVersionData();

        Intent intent = new Intent(this, AutoService.class);
        startService(intent);
        code = getLocalVersion(this);
    }
    //获取版本号
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            Log.d("TAG", "当前版本号：" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    private void iconAnimation() {
        alphaAnimation = new AlphaAnimation(1, 0);
//        alphaAnimation.setRepeatCount(3000);
        alphaAnimation.setDuration(3000);
        icon.setVisibility(View.VISIBLE);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                icon.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                icon.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        icon.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }

    @Override
    protected void initView() {

        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
        correntTv = (TextView) findViewById(R.id.correntTv);
        icon = (ImageView) findViewById(R.id.icon);
        if (!SpUtil.getString(this, FiannceConstants.SP_TOKEN).equals("")) {
            Intent intent = new Intent(this, AutoService.class);
            startService(intent);
        }

        iconAnimation();
        correntTv.setText("当前版本:1.0");
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
    public void onProductDara(ProductBean productBean) {

    }

    @Override
    public void showLoading() {
//        Dialog();
    }

    private void Dialog() {
        alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle("下载最新版本");
        alerDialog.setMessage("解决一些bug,优化网络请求!");
        alerDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                    ARouter.getInstance().build(getString(R.string.main_mainActivity)).withInt("", 1).navigation();
                    finish();

            }
        });
        alerDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent = new Intent(WelcomeActivity.this, AutoService.class);
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        AutoService.AutoBinder fiannceBinder = (AutoService.AutoBinder) iBinder;
                        AutoService fiannceService = fiannceBinder.getAutoService();
                        fiannceService.setDownLoad(versionBean.getResult().getApkUrl());

                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {

                    }
                },BIND_AUTO_CREATE);
            }
        });
        alerDialog.show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,"请求出现错误："+error,Toast.LENGTH_SHORT);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown>0) {
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    } else {
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;
                case ONE_TASK_FIISH:
                    if (homeFinsh&&versionFinsh&&advertistFinsh) {
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;
                case ALL_TASK_FIISH:
                    Dialog();
                    break;
            }
        }
    };

    @Override
    public void destroy() {
        super.destroy();
        alphaAnimation.cancel();
        handler.removeCallbacksAndMessages(null);
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

    @Override
    public void onLoginChange(LoginBean isLogin) {

    }
}
