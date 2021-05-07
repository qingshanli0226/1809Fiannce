package com.finance.zg6.welcome;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.finance.framework.BaseActivity;
import com.finance.framework.manager.CacheManager;
import com.finance.framework.sp.Constant;
import com.finance.framework.sp.SPUtil;
import com.finance.net.bean.HomeBean;
import com.finance.net.bean.VersionBean;
import com.finance.net.model.FinanceApiService;
import com.finance.user.service.AutoService;
import com.finance.zg.R;
import com.finance.zg6.main.MainActivity;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView countDownTv;
    private int countDownTime = 3;
    private ProgressBar progressBar;

    private final int ONE_TASK_FINISH = 0;
    private final int All_TASK_FINISH = 1;

    private Intent intent;
    private VersionBean version;

    private boolean homeFinish = false;
    private boolean versionFinish = false;
    private boolean allFinish = false;

    private  ServiceConnection serviceConnection;

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


        intent = new Intent(this,AutoService.class);

        httpPresenter.getHomeData();
        httpPresenter.getVersionData();

        countDownTv.setText(getString(R.string.down_time)+countDownTime+getString(R.string.time));
        handler.sendEmptyMessageDelayed(ONE_TASK_FINISH,1000);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initView() {
        requestPermissions(new String[]{"android.permission.CALL_PHONE"
                ,"android.permission.WRITE_EXTERNAL_STORAGE"
                ,"android.permission.READ_EXTERNAL_STORAGE"
                ,"android.permission.SYSTEM_ALERT_WINDOW"},100);

        countDownTv = (TextView) findViewById(R.id.countDown);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //启动自动登录服务
        if(!SPUtil.getString(this, Constant.SP_TOKEN).equals("")){
            Intent intent = new Intent(this, AutoService.class);
            startService(intent);
        }
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        LogUtils.json(homeBean);
        homeFinish = true;
        handler.sendEmptyMessage(All_TASK_FINISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        version = versionBean;
        Toast.makeText(this, getString(R.string.welcomeActivity_latest_version_number_toast) + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        versionFinish = true;
        handler.sendEmptyMessage(All_TASK_FINISH);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==ONE_TASK_FINISH){
                countDownTime--;
                if (countDownTime<=0){
                    countDownTv.setText(getString(R.string.down_time)+countDownTime+getString(R.string.time));
                    allFinish = true;
                    handler.sendEmptyMessageDelayed(All_TASK_FINISH,1000);
                }else {
                    countDownTv.setText(getString(R.string.down_time)+countDownTime+getString(R.string.time));
                    handler.sendEmptyMessageDelayed(ONE_TASK_FINISH,1000);
                }
            }
            else if (msg.what == All_TASK_FINISH){
                if (homeFinish&&versionFinish&&allFinish){
                    AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                    builder.setTitle(getString(R.string.welcomeActivity_alert_title));
                    builder.setMessage(getString(R.string.welcomeActivity_alert_message));
                    builder.setNegativeButton(getString(R.string.welcomeActivity_alert_button_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(WelcomeActivity.this, getString(R.string.welcomeActivity_alert_success_toast), Toast.LENGTH_SHORT).show();
//                    ARouter.getInstance().build("/main/MainActivity").withInt("",1).navigation();
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setPositiveButton(getString(R.string.welcomeActivity_alert_button_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
//                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                            progressDialog.setTitle(getString(R.string.welcomeActivity_alert_title));
//                            progressDialog.setMessage(getString(R.string.welcomeActivity_progress_message));
//                            progressDialog.setProgress(100);
//                            progressDialog.setCancelable(true);
//                            progressDialog.show();

                            serviceConnection = new ServiceConnection() {
                                @Override
                                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                                    AutoService.FiannceBinder financeBinder= (AutoService.FiannceBinder) iBinder;

                                    AutoService autoService=financeBinder.getFiannceService();

                                    autoService.DownLoad(version.getResult().getApkUrl());
//
                                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onServiceDisconnected(ComponentName componentName) {

                                }
                            };
                            bindService(intent,serviceConnection,BIND_AUTO_CREATE);

                        }
                    });
                    builder.show();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (serviceConnection!=null){
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