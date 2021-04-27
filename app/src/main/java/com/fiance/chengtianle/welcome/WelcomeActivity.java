package com.fiance.chengtianle.welcome;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiance.chengtianle.R;
import com.fiance.framework.BaseActivity;
import com.fiance.framework.CacheManager;
import com.fiance.net.mode.HomeBean;
import com.fiance.net.mode.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    private TextView contentTv;
    private android.widget.ProgressBar progressBar;
    private TextView coundDownTv;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private int countDown = 3;

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
//      contentTv.setText(homeBean.toString());
        loadingPage.showSuccessView();
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        loadingPage.showSuccessView();
        versionFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

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
        contentTv = findViewById(R.id.countDownTv);
        progressBar = findViewById(R.id.progressBar);
        coundDownTv = findViewById(R.id.countDownTv);
        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
        coundDownTv.setText(countDown + "秒");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
//      loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0) {
                        coundDownTv.setText(countDown + "秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
                    } else {
                        coundDownTv.setText(countDown + "秒");
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;

                case ONE_TASK_FIISH:
                    if (homeFinsh && versionFinsh && advertistFinsh) {
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;
                case ALL_TASK_FIISH:
                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build("/main/MainActivity").withInt("", 1).navigation();
                    finish();
                    break;
            }
        }
    };

    @Override
    public void showError(String error) {
//        loadingPage.showTransparentLoadingView();

    }

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }
}