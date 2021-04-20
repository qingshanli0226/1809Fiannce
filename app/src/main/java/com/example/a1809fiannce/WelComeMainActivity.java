package com.example.a1809fiannce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1809fiannce.update.UpdatePresenter;
import com.example.formwork.model.HomeBean;
import com.example.formwork.model.UpdateBean;
import com.example.network.BaseActivity;

public class WelComeMainActivity extends BaseActivity<UpdatePresenter> implements CallBack{
    private TextView miao;
    private ProgressBar bar;
    private final int ONE_TASK=0;
    private final int ALL_TASK=1;
    private final int DELAY_INDEX=2;
    private final int DELAY=1*1000;
    private boolean HomeFinsh=false;
    private boolean UpdateFinsh=false;
    private boolean AddFinsh=false;
    private int count=3;
    private UpdateBean bean;
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
            mPresenter=new UpdatePresenter(this);
            mPresenter.HomeData();
            mPresenter.UpdateData();
    }

    @Override
    protected void initView() {
        miao = (TextView) findViewById(R.id.miao);
        bar = (ProgressBar) findViewById(R.id.bar);
        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
        miao.setText(count+"秒");
    }

    @Override
    protected int FindLayout() {
        return R.layout.activity_wel_come_main;
    }

    @Override
    public void HomeData(HomeBean homeBean) {
        Log.i("zx", "HomeData: "+homeBean.toString());
        HomeFinsh=true;
        HomeCallBack.getHomeCallBack().setHomeBean(homeBean);
        handler.sendEmptyMessage(ONE_TASK);
    }

    @Override
    public void UpdateData(UpdateBean updateBean) {
        bean=updateBean;
        UpdateFinsh=true;
        handler.sendEmptyMessage(ONE_TASK);

    }

    @Override
    public void ShowLoading() {
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideLoading() {
        bar.setVisibility(View.GONE);
    }

    @Override
    public void Error(String error) {
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
                            startActivity(new Intent(WelComeMainActivity.this,MainActivity2.class));
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
}