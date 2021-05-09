package com.example.a1809fiannce.welcome;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1809fiannce.home.HomeCallBack;
import com.example.a1809fiannce.main.MainActivity2;
import com.example.a1809fiannce.R;
import com.example.framwork.base.BaseActivity;
import com.example.network.model.HomeBean;
import com.example.network.model.UpdateBean;


public class WelComeMainActivity extends BaseActivity<WelcomePresenter> implements WelcomeCallBack {
    private TextView miao;
    private final int ONE_TASK=0;
    private final int ALL_TASK=1;
    private final int DELAY_INDEX=2;
    private final int DELAY=1*1000;
    private boolean HomeFinsh=false;
    private boolean UpdateFinsh=false;
    private boolean AddFinsh=false;
    private int count=3;
    private UpdateBean bean;
    private UpdateService updateService;
    private ServiceConnection serviceConnection;
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
                        all();
                    break;
            }
        }
    };
    @Override
    protected void initData() {
            mPresenter=new WelcomePresenter(this);
            mPresenter.HomeData();
            mPresenter.UpdateData();
            serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                UpdateService.MyBind myBind = (UpdateService.MyBind) service;
                updateService = myBind.MyService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(WelComeMainActivity.this, UpdateService.class);
        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void initView() {
        miao = (TextView) findViewById(R.id.miao);
        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
        miao.setText(count+"秒");

    }

    @Override
    protected int FindLayout1() {
        return R.layout.activity_wel_come_main;
    }

    @Override
    public void HomeData(HomeBean homeBean) {

        HomeFinsh=true;
        HomeCallBack.getHomeCallBack().setHomeBean(homeBean);
        handler.sendEmptyMessage(ONE_TASK);
        pageView.ShowSuccess();
    }

    @Override
    public void UpdateData(UpdateBean updateBean) {
        bean=updateBean;
        UpdateFinsh=true;
        handler.sendEmptyMessage(ONE_TASK);

    }


    @Override
    public void ShowLoading() {
//            pageView.ShowLoad();
//        bar.setVisibility(View.VISIBLE);


    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {
//        pageView.ShowError(error);
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
    public void all(){
        if (bean!=null){
            int code = bean.getCode();
            if (code==200){
                String version = bean.getResult().getVersion();
                double ver = Double.parseDouble(version);
                Double a = a();
                if (ver>a){
                    AlertDialog.Builder builder = new AlertDialog.Builder(WelComeMainActivity.this);
                    builder.setTitle("版本更新");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //CallUpdate.getInstance().setUrl("http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg");
                            //开启一个服务下载
//                            startService(new Intent(WelComeMainActivity.this,UpdateService.class));
                            //http://49.233.0.68:8080//atguigu/apk/P2PInvest/app-debug.apk
//                            Log.i("aa", "onClick: "+"http://49.233.0.68:8080/"+bean.getResult().getApkUrl().split("9999")[1]);
                            updateService.DownUpdate("http://49.233.0.68:8080//atguigu/apk/P2PInvest/app-debug.apk");
                            //进入主页面
                            startActivity(new Intent(WelComeMainActivity.this, MainActivity2.class));
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(WelComeMainActivity.this,MainActivity2.class));
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
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        mPresenter.delView();
        unbindService(serviceConnection);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}