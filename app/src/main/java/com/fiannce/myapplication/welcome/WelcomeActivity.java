package com.fiannce.myapplication.welcome;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;


import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.framework.view.LoadingPage;
import com.fiannce.myapplication.R;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private int countDown = 3;

    @Override
    protected void initView() {

        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);

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
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

        Toast.makeText(this, "获取到版本信息：" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        if (versionBean.getResult().getVersion().equals("1.0")) {
            versionFinsh = true;
            handler.sendEmptyMessage(ONE_TASK_FIISH);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
            builder.setTitle("下载最新版本");
            builder.setMessage("解决一些bug,优化网络请求");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    versionFinsh = true;
                    handler.sendEmptyMessage(ONE_TASK_FIISH);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    versionFinsh = true;
                    handler.sendEmptyMessage(ONE_TASK_FIISH);
                }
            });
            builder.show();
        }

        loadingPage.showSuccessView();
    }

    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "请求出现错误：" + error, Toast.LENGTH_SHORT);
        loadingPage.showError(error);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0) {
                        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
                    } else {
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
    public void destory() {
        super.destory();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}
