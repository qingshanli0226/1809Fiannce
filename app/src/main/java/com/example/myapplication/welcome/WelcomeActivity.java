package com.example.myapplication.welcome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.FiannceUserManager;
import com.example.model.HomeBean;
import com.example.model.ProductBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;
import com.example.myapplication.apk.APKVersionCodeUtils;
import com.example.demo.Demo;
import com.example.user.autologin.UserService;

import java.io.File;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView contenNum;
    private final int ONE_TASK_FINISH = 0;
    private final int All_TASK_FINISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1000;
    private boolean home_finish = false;
    private boolean version_finish = false;
    private boolean advertistFinsh  = false;
    private int FINISH_SESSION = 0;
    private int session = 3;
    private VersionBean versionBeans;
    private Intent intent;

    private UserService myservice;
    private ServiceConnection serviceConnection;

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();
        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(WelcomeActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 10);
            } else {
                Toast.makeText(WelcomeActivity.this, "granted show-- 悬浮窗", Toast.LENGTH_SHORT);
                window();
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                if (requestCode == 10) {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(WelcomeActivity.this, "not granted", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(WelcomeActivity.this, "granted show 悬浮窗", Toast.LENGTH_SHORT);
                    window();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Button install;

    public void window(){
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //设置小窗口尺寸的类
        layoutParams = new WindowManager.LayoutParams();
        //设置窗口的类型为系统类型，系统类型的窗口显示应用窗口的上方.系统窗口可以在Service中显示,普通Dialog不可以的
        layoutParams.type= WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //像素格式为透明的
        layoutParams.format = PixelFormat.TRANSPARENT;

        //设置该flag在显示该小窗口时，其他窗口的按钮或者其他控件都可以点击.
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置小窗口的尺寸
        //单位是像素
        layoutParams.width=700;
        layoutParams.height=500;

        //生成一个窗口的布局view，并且将该view添加到窗口里.
        View rootView = LayoutInflater.from(this).inflate(com.example.user.R.layout.window_ijk, null);
        windowManager.addView(rootView, layoutParams);


        install = rootView.findViewById(com.example.user.R.id.window_install);
        install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "安装成功", Toast.LENGTH_SHORT).show();
                windowManager.removeView(rootView);
            }
        });
        windowManager.addView(rootView, layoutParams);
    }

    @Override
    protected void initView() {

        //动态权限添加权限
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},100);
        }
        requestPermission();
        contenNum = (TextView) findViewById(R.id.conten_num);
        contenNum.setText(session+"秒");
        intent = new Intent(this, UserService.class);
        startService(intent);

        //绑定服务
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                UserService.MyBinder myBinder = (UserService.MyBinder) service;
                myservice = myBinder.getMyService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);



    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        //将下载的数据存储到单例里面,方便下一个页面使用
        CacheManager.getInstance().setHomeBean(homeBean);
        home_finish = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息:"+versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        version_finish = true;
        versionBeans = versionBean;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void onProductDara(ProductBean productBean) {

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    session--;
                    if (session > FINISH_SESSION){
                        contenNum.setText(session+"秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    }else {
                        contenNum.setText(session+"秒");
                        advertistFinsh=true;
                        handler.sendEmptyMessage(ONE_TASK_FINISH);
                    }
                    break;
                case ONE_TASK_FINISH:
                    if (home_finish==true && version_finish==true && advertistFinsh==true){
                        handler.sendEmptyMessage(All_TASK_FINISH);
                    }
                    break;
                case All_TASK_FINISH:
                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();

                    int code = versionBeans.getCode();
                    if (code == Demo.VERSION_CODE){
                        VersionBean.ResultBean result = versionBeans.getResult();
                        int versionCode = result.getVersionCode();
                        int versionCode1 = APKVersionCodeUtils.getVersionCode(WelcomeActivity.this);
                        if (versionCode1<versionCode){

                            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                            builder.setIcon(R.drawable.ic_launcher_background);
                            builder.setMessage(Demo.HINT_DIALOG_MESSAGE);
                            builder.setTitle(Demo.HINT_DIALOG_TITLE);
                            builder.setCancelable(true);

                            //点击确定之后下载文件
                            builder.setPositiveButton(Demo.HINT_DIALOG_CONFIRM, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    //判断是否下载过这个文件 如果下载过直接跳转
                                    String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Demo.SDCARD_ADDRESS;
                                    File file = new File(apkPath);
                                    if (file.exists()){
                                        Toast.makeText(WelcomeActivity.this, "已下载完成", Toast.LENGTH_SHORT).show();
                                        ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();
                                        return ;
                                    }else {
                                        ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();
                                        //启动服务
                                        myservice.downLoad(Demo.DOWNLOAD_ADDRESS);
                                    }

                                }
                            });

                            builder.setNegativeButton(Demo.HINT_DIALOG_CANCLE, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();
                                    finish();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();


                        }
                    }else {
                        ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();
                        finish();
                    }
                    break;
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        //解除绑定
        unbindService(serviceConnection);
        //停止服务
        stopService(intent);
    }
}