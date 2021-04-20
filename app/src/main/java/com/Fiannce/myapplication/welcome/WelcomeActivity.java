package com.Fiannce.myapplication.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.Fiannce.myapplication.R;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    private TextView contentTv;
    private TextView coundDownTv;
    private ProgressBar progressBar;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1*1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private int countDown = 3;

    @Override
    protected void initView() {
        contentTv = (TextView) findViewById(R.id.contentTv);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        coundDownTv = (TextView) findViewById(R.id.countDownTv);
        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
        coundDownTv.setText(countDown+"秒");
    }

    @Override
    protected void initPresenter() {
        httppresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        httppresenter.getHomeData();
        httppresenter.getVersionData();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        contentTv.setText(homeBean+"");
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息：" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        versionFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
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
    public void showError(String error) {
        Toast.makeText(this, "请求出现错误：" + error, Toast.LENGTH_SHORT);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case DELAY_INDEX :
                    countDown--;
                    if (countDown > 0){
                        coundDownTv.setText(countDown+"秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    }else {
                        coundDownTv.setText(countDown+"秒");
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;
                case ONE_TASK_FIISH:
                    if (homeFinsh&&versionFinsh&&advertistFinsh){
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;
                case ALL_TASK_FIISH:
                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build("/main/MainActivity").withInt("",1).navigation();
                    finish();
                    break;
            }
        }
    };

    @Override
    public void destory() {
        super.destory();
        handler.removeCallbacksAndMessages(null);
    }
}
