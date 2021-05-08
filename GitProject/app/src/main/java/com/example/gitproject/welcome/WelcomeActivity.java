package com.example.gitproject.welcome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.gitproject.MainActivity;
import com.example.gitproject.R;

import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdateBean;
import com.example.user.service.UserService;

import java.util.List;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    private final int ONE_TASK_FIISH = 0;
    private ImageView welImg;
    private final int COUNT_TIME = 1;
    private boolean task_one = false;
    private boolean task_two = false;
    private boolean task_three = false;
    private int count = 3;
    private TextView countDown;
    private UpdateBean updateBean;
    private UserService myService;
    private ServiceConnection serviceConnection;


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {

        //权限
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.SYSTEM_ALERT_WINDOW},100);
        }
        requestPermission();
        countDown = (TextView) findViewById(R.id.countDown);
        welImg = (ImageView) findViewById(R.id.wel_img);
    }

    //权限
    private void requestPermission() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(WelcomeActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 10);
            } else {
                Toast.makeText(WelcomeActivity.this, "granted show-- 悬浮窗", Toast.LENGTH_SHORT);
            }
        }
    }

    @Override
    public void initPresenter() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        //请求数据
        mPresenter.getDataHome();
        mPresenter.getAppUpdate();
        handler.sendEmptyMessageDelayed(COUNT_TIME, 1000);

        //绑定服务
        Intent intent = new Intent(this, UserService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                UserService.MyBinder myBinder = (UserService.MyBinder) service;
                myService = myBinder.getMyService();
                myService.init(WelcomeActivity.this);
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        startService(intent);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == COUNT_TIME) {
                count--;
                if (count < 0) {
                    task_one = true;
                    handler.sendEmptyMessage(ONE_TASK_FIISH);
                } else {
                    countDown.setText(count + "s");
                    handler.sendEmptyMessageDelayed(COUNT_TIME, 1000);
                }
            } else if (msg.what == ONE_TASK_FIISH) {
                if (task_one && task_two && task_three) {
                    //跳转
                    showAlert();
                }
            } else if(msg.what == 7){
                Long obj = (Long) msg.obj;
                int progress = progressDialog.getProgress();

                progressDialog.setProgress(progress+obj.intValue());
                if(progress == 100){
                    progressDialog.hide();
                    ARouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
                    finish();
                }
            }

        }
    };
    private ProgressDialog progressDialog;
    //弹出对话框
    private void showAlert() {
        int oldCode = 0;
        try {
            oldCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //判断版本
        if (oldCode < updateBean.getResult().getVersionCode()) {
            //提示更新
            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
            String wel_alert_title = getString(R.string.wel_alert_title);
            String wel_alert_no = getString(R.string.wel_alert_no);
            String wel_alert_yes = getString(R.string.wel_alert_yes);
            builder.setTitle(wel_alert_title);
            builder.setMessage(updateBean.getResult().getDesc());
            builder.setNegativeButton(wel_alert_yes, new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //下载新app
//                    progressDialog  = new ProgressDialog(WelcomeActivity.this);
//                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                    progressDialog.show();

                    //启动下载服务服务
                    myService.downLoad("http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk",handler);
                    ARouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
                    finish();

                }
            });
            builder.setPositiveButton(wel_alert_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //跳转界面
                    //关闭此页面
                    ARouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            builder.show();

        } else {
            //最新版本
            finish();
            ARouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(WelcomeActivity.this, "not granted", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(WelcomeActivity.this, "granted show 悬浮窗", Toast.LENGTH_SHORT);
                }
            }
        }
    }
    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        task_two = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onAppUpdate(UpdateBean updateBean) {
        this.updateBean = updateBean;
        task_three = true;
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
        loadPage.showErrorText(error);
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;
        return super.onKeyDown(keyCode, event);
    }//屏蔽返回键

    @Override
    public void destroy() {
        super.destroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        unbindService(serviceConnection);
    }

}