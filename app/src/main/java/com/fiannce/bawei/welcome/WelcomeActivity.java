package com.fiannce.bawei.welcome;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.bawei.MainActivity;
import com.fiannce.commond.CommonConstant;
import com.fiannce.commond.SpUtil;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;
import com.fiannce.user.service.AutoService;
import com.fiannce.zhaoyuzan.R;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView coundDownTv;
    private int countDown = 3;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private VersionBean versionBean;
    private ServiceConnection serviceConnection;
    private int D_code;
    private Intent intent;
    private PackageManager packageManager;


    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {

        requestPermissions(new String[]{"android.permission.CALL_PHONE"
                , "android.permission.WRITE_EXTERNAL_STORAGE"
                , "android.permission.READ_EXTERNAL_STORAGE"
                , "android.permission.SYSTEM_ALERT_WINDOW"}, 100);

        httpPresenter.getHomeData();
        httpPresenter.getVersionData();

        intent = new Intent(this, AutoService.class);
        startService(intent);

        D_code = getLocalVersion(this);
    }


    @Override
    protected void initView() {
        coundDownTv = findViewById(R.id.countDownTv);

        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
        coundDownTv.setText(countDown + getString(R.string.miao));

        if (!SpUtil.getString(this, CommonConstant.SP_TOKEN).equals("")) {
            Intent intent = new Intent(this, AutoService.class);
            startService(intent);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        this.versionBean = versionBean;
        Toast.makeText(this, getString(R.string.banben_info) + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
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
    public void showToast(String msg) {
        loadingPage.showError(msg);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0) {
                        coundDownTv.setText(countDown + getString(R.string.miao));
                        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
                    } else {
                        coundDownTv.setText(countDown + getString(R.string.miao));
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
                    if (D_code < versionBean.getResult().getVersionCode()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);

                        builder.setTitle(getResources().getString(R.string.new_banben));
                        builder.setMessage(getResources().getString(R.string.bug));

                        builder.setNegativeButton(getResources().getString(R.string.cancle), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                serviceConnection = new ServiceConnection() {
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
                                };

                                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                            }
                        });

                        builder.show();
                    } else {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
            }
        }
    };


    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }
}
