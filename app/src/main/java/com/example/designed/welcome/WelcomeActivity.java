package com.example.designed.welcome;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.designed.MainActivity;
import com.example.designed.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.FiannceService;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.Libean;
import com.fiannce.bawei.net.model.VersionBean;



public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    WelcomePresenter welcomePresenter;
    private final int ONE_FILSH = 0;
    private Intent intent;
    private final int ALLFILSH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAT = 1*1000;//1秒
    private boolean homeFinsih = false;
    private boolean versioneFinsih = false;
    private boolean LIFinsih = false;
    private boolean advFinsih = false;
    private ImageView iv;
    private ProgressBar progressBar;
    private TextView dao;
    private int countDown = 3;

    private  ServiceConnection serviceConnection;

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        homeFinsih =true;
        handler.sendEmptyMessage(ONE_FILSH);
        loadingPage.showSuccessView();
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息:" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        versioneFinsih = true;
        handler.sendEmptyMessage(ONE_FILSH);
        loadingPage.showSuccessView();
    }

    @Override
    public void onLiData(Libean libean) {
        CacheManager.getInstance().setLibean(libean);
        LIFinsih = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {

        requestPermissions(new String[]{"android.permission.CALL_PHONE"
                ,"android.permission.READ_EXTERNAL_STORAGE"
                ,"android.permission.WRITE_EXTERNAL_STORAGE"
                ,"android.permission.CALL_PHONE"
                ,"android.permission.SYSTEM_OVERLAY_WINDOW"
                ,"android.permission.ACCESS_NETWORK_STATE "
           ,"android.permission.ACTION_IMAGE_CAPTURE" },100);

        welcomePresenter.getHomeData();
        welcomePresenter.getVersionData();
        welcomePresenter.getLiData();


        intent = new Intent(this,FiannceService.class);
        startService(intent);

    }

    @Override
    protected void initPresenter() {
      welcomePresenter = new WelcomePresenter(this);

    }

    @Override
    protected int getLaoutId() {
        return R.layout.welcome;
    }



    @Override
    protected void initView() {

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        iv = (ImageView) findViewById(R.id.iv);
        dao = (TextView) findViewById(R.id.dao);
        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAT);

        dao.setText("倒计时"+countDown);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
            Toast.makeText(this, "请求出现错误" + error, Toast.LENGTH_SHORT).show();
            loadingPage.showError(error);
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case DELAY_INDEX:
                    countDown--;
                    if (countDown>0){
                        dao.setText("倒计时:" + countDown);
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAT);
                    }else {
                        dao.setText("倒计时:"+countDown);
                        advFinsih  = true;
                        handler.sendEmptyMessage(ONE_FILSH);
                    }

                    break;

                case ONE_FILSH:

                    if (homeFinsih&&versioneFinsih&&advFinsih&&LIFinsih){
                        handler.sendEmptyMessage(ALLFILSH);
                    }

                    break;

                case ALLFILSH:

                    AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                    builder.setTitle("下载最新版本");
                    builder.setMessage("解决一些bug,优化网络请求");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent();
                            intent.setAction("wang");
                            startActivity(intent);
                        }
                    });

                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            serviceConnection  = new ServiceConnection(){

                                @Override
                                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                                    FiannceService.FiannceBinder fiannceBinder = (FiannceService.FiannceBinder) iBinder;

                                    FiannceService fiannceService = fiannceBinder.getFiannceService();
                                    fiannceService.DownLoad(null);
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


                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                   // ARouter.getInstance().build("/main/MainActivity").withInt("",1).navigation();



                    break;
                    default:break;
            }

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

        if (serviceConnection != null){
            unbindService(serviceConnection);
        }


    }

    @Override
    public void onDisconnected() {
        super.onDisconnected();
        Toast.makeText(this, "没网", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected() {
        super.onConnected();
        Toast.makeText(this, "有网", Toast.LENGTH_SHORT).show();
    }
}
