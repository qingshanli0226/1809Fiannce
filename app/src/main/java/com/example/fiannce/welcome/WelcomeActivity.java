package com.example.fiannce.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.fiannce.MainActivity;
import com.example.fiannce.R;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.net.mode.HomeBean;
import com.example.net.mode.VersionBean;

import java.util.ArrayList;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView contentTv;
    private TextView coundDownTv;
    private ProgressBar progressBar;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private int countDown = 3;

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
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();
    }

    @Override
    protected void initView() {
        contentTv = findViewById(R.id.contentTv);
        progressBar = findViewById(R.id.progressBar);
        coundDownTv = findViewById(R.id.countDownTv);

        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
        coundDownTv.setText(countDown+"秒");
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
//        contentTv.setText(homeBean.toString());
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息"+ versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
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
    public void showError(String error) {
        Toast.makeText(this, "请求出现错误", Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0){
                        coundDownTv.setText(countDown+"秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    }else{
                        coundDownTv.setText(countDown+"秒");
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;

                case ONE_TASK_FIISH:
                    if (homeFinsh && versionFinsh && advertistFinsh){
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;

                case ALL_TASK_FIISH:
                    Log.d("TAG", "handleMessage: 所有任务完成");
                    Toast.makeText(WelcomeActivity.this, "1111111111", Toast.LENGTH_SHORT).show();


                    AlertDialog.Builder log = new AlertDialog.Builder(WelcomeActivity.this);
                    log.setMessage("是否更新版本");
                    log.setPositiveButton("确定",null).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {

                        }
                    });

                    log.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ARouter.getInstance().build("/main/MainActivity").withInt("",1).navigation();

                            finish();
                        }
                    });

                    AlertDialog alertDialog = log.create();
                    alertDialog.show();
                    break;
            }
        }
    };

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }
}