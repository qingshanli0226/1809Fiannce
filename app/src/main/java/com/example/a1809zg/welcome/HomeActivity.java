package com.example.a1809zg.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809zg.MainActivity;
import com.example.a1809zg.R;
import com.example.frame.BaseActivity;
import com.example.frame.CacheMore;
import com.example.frame.FiannceService;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.UpdataBean;
import com.example.user.AutoService;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeView {
private HomePresenter homePresenter;
    private android.widget.TextView miao;
    private android.widget.ProgressBar progressBar;
    private final int ONE_TASK=0;
    private final int ALL_TASK=1;
    private final int DELAY_INDEX=2;

    private final int DELAY=1*1000;
    private boolean HomeFinsh=false;
    private boolean UpdateFinsh=false;
    private boolean AddFinsh=false;
    private int count=3;
    private UpdataBean bean;
    private Intent intent;

    private Handler handler=new Handler(){
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case DELAY_INDEX:
                    count--;
                    if (count>0){
                        miao.setText(count+"秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    }else {
                        miao.setText(count+"秒");
                        AddFinsh=true;
                        handler.sendEmptyMessage(ONE_TASK);
                    }
                    break;
                case ONE_TASK:
                    if (HomeFinsh&&UpdateFinsh&&AddFinsh){
                        handler.sendEmptyMessage(ALL_TASK);
                    }
                    break;
                case ALL_TASK:

                    Toast.makeText(HomeActivity.this, "1231231", Toast.LENGTH_SHORT).show();
                    all();
                    break;
            }
        }
    };
    @Override
    public void onHomeData(HomeBean homeBean) {
        HomeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK);
        CacheMore.getInstance().setHomeBean(homeBean);

    }

    @Override
    public void onUpdaData(UpdataBean updataBean) {
        Toast.makeText(this, "获取到版本信息："+ updataBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        UpdateFinsh=true;
        bean = updataBean;
        handler.sendEmptyMessage(ONE_TASK);
        loadingPage.showSuccessView();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initPresenter() {
         homePresenter = new HomePresenter(this);
    }

    @Override
    protected void initData() {
         homePresenter.getHomeData();
         homePresenter.getVersionData();
         handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"
                ,"android.permission.READ_EXTERNAL_STORAGE"},100);

        intent = new Intent(this, FiannceService.class);
        startService(intent);

    }

    @Override
    protected void initView() {

        miao = findViewById(R.id.contentTv);
        progressBar = findViewById(R.id.progressBar);
        Intent intent = new Intent(this, AutoService.class);
        startService(intent);
    }

    @Override
    public void showLoaing() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
          Toast.makeText(this, "错误"+msg, Toast.LENGTH_SHORT).show();
    }
    public void all(){
        if (bean!=null){

            int code = bean.getCode();
            if (code==200){
                String version = bean.getResult().getVersion();
                double ver = Double.parseDouble(version);
                Double a = a();
                if (ver>a){
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                    builder.setTitle("版本更新");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            bindService(intent, new ServiceConnection() {
                                @Override
                                public void onServiceConnected(ComponentName name, IBinder service) {
                                    LogUtils.d("1233");

                                    FiannceService.FiannceBinder fiannceBinder= (FiannceService.FiannceBinder) service;
                                    FiannceService fiannceService = fiannceBinder.getFiannceService();
                                    fiannceService.DownLoad(bean.getResult().getApkUrl());

                                }

                                @Override
                                public void onServiceDisconnected(ComponentName name) {

                                }
                            },BIND_AUTO_CREATE);

                            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(HomeActivity.this, MainActivity.class));
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        }
    }
    public Double a(){
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo =null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = packageInfo.versionName;
        double num = Double.parseDouble(versionName);
        return num;
    }

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(true);

        homePresenter.detachView();

    }

    @Override
    public void onConnect() {
         Toast.makeText(this, "有网络了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }
}