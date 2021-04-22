package com.example.a1809zg.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.a1809zg.CustomBean;
import com.example.a1809zg.MainActivity;
import com.example.a1809zg.R;
import com.example.frame.BaseActivity;
import com.example.frame.CacheMore;
import com.example.net.bean.HomeBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.UpdataBean;

public class MainActivity2 extends BaseActivity<Ipresenter> implements Iview {
private Ipresenter ipresenter;
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

                    Toast.makeText(MainActivity2.this, "1231231", Toast.LENGTH_SHORT).show();
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
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initPresenter() {
         ipresenter = new Ipresenter(this);
    }

    @Override
    protected void initData() {
         ipresenter.getHomeData();
         ipresenter.getVersionData();
         handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
    }

    @Override
    protected void initView() {

        miao = findViewById(R.id.contentTv);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void showLoaing() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("版本更新");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity2.this,MainActivity.class));
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity2.this, MainActivity.class));
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