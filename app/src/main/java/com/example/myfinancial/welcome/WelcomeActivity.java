package com.example.myfinancial.welcome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.AppVerSion;
import com.example.framework.BaseActivity;
import com.example.framework.CacheLoadMore;
import com.example.myfinancial.R;
import com.example.net.bean.HomeBean;
import com.example.net.bean.VersionBean;
import com.example.user.service.FiannceService;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelComeView {
    private android.widget.TextView countdowntv;
    private final int ONE_TASK_FINISH = 0;
    private final int ALL_TASK_FINISH = 1;
    private final int DELAT_INDEX = 2;//标识符
    private final int DELAY = 1 * 1000;//1秒
    private boolean geth = false;
    private boolean getv = false;
    private boolean drawtime = false;
    private int drawtimetv = 3;
    private int newAppVerSionCode;
    private int currentAppVerSionCode;

    private FiannceService.MyBinder myBinder ;
    private String apkPath;

    @Override
    public int getbandLayout() {
        return R.layout.activity_welcome2;
    }

    @Override
    public void initPresenter() {
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getVersion();
        mPresenter.getHome();
    }

    @Override
    public void initView() {
        //系统弹框权限
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 10);
            } else {
                Toast.makeText(this, "granted show-- 悬浮窗", Toast.LENGTH_SHORT).show();
            }
        }



//        向上顶  全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        countdowntv = (TextView) findViewById(R.id.countDownTv);
        handler.sendEmptyMessageDelayed(DELAT_INDEX, DELAY);//发送倒计时  handler
        countdowntv.setText(drawtimetv + "");//修改
    }


    @Override
    public void initWelcome(HomeBean homeBean) {
        Log.d("WelcomeActivity", homeBean.toString());
        geth = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
        CacheLoadMore.getInstance().setHomeBean(homeBean);
    }

    @Override
    public void initVersion(VersionBean versionBean) {
        Log.d("WelcomeActivity", versionBean.toString());
        newAppVerSionCode = versionBean.getResult().getVersionCode();//最新版本号
        apkPath ="http://49.233.0.68:8080/atguigu/apk/P2PInvest/app-debug.apk";//获取下载链接
        getv = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAT_INDEX:
                    drawtimetv--;
                    if (drawtimetv > 0) {
                        countdowntv.setText(drawtimetv + "");
                        handler.sendEmptyMessageDelayed(DELAT_INDEX, DELAY);
                    } else {
                        countdowntv.setText(drawtimetv + "");
                        handler.sendEmptyMessage(ONE_TASK_FINISH);
                        drawtime = true;
                    }
                    break;
                case ONE_TASK_FINISH:
                    if (geth && getv && drawtime) {
                        handler.sendEmptyMessage(ALL_TASK_FINISH);
                    }
                    break;
                case ALL_TASK_FINISH:
                    Toast.makeText(WelcomeActivity.this, "全部运行完成", Toast.LENGTH_SHORT).show();
                    currentAppVerSionCode = AppVerSion.getVersionCode(WelcomeActivity.this);//当前版本号
                    if (currentAppVerSionCode < newAppVerSionCode) {//需要更新
                        //对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                        builder.setTitle("检测到新版本");
                        builder.setMessage("检测到新版本  是否下载安装");
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ARouter.getInstance().build("/main/MainActivity").navigation();
                                finish();
                            }
                        });
                        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(WelcomeActivity.this, "正在下载", Toast.LENGTH_SHORT).show();

                                ARouter.getInstance().build("/main/MainActivity").navigation();
                                finish();

                                Intent intent = new Intent(WelcomeActivity.this, FiannceService.class);
                                ServiceConnection serviceConnection = new ServiceConnection() {
                                    @Override
                                    public void onServiceConnected(ComponentName name, IBinder service) {
                                        myBinder = (FiannceService.MyBinder) service;
                                        myBinder.myMethod(apkPath);
                                    }

                                    @Override
                                    public void onServiceDisconnected(ComponentName name) {

                                    }
                                };
                                //绑定服务
                                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else {//不需要更新
                        ARouter.getInstance().build("/main/MainActivity").navigation();
                        finish();
                    }
                    break;
            }
        }
    };


    //加载页面期间不可返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(this, "not granted", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(this, "granted show 悬浮窗", Toast.LENGTH_SHORT);
                }
            }
        }
    }
}