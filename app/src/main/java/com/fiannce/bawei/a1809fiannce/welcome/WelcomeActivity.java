package com.fiannce.bawei.a1809fiannce.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.bawei.a1809fiannce.MainActivity;
import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.VersionBean;

import androidx.annotation.NonNull;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView{
    private TextView contentTv;
    private TextView coundDownTv;
    private ProgressBar progressBar;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1*1000;//1秒
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
        contentTv.setText(homeBean.toString());
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);

    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息："+ versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this,"请求出现错误："+error,Toast.LENGTH_SHORT);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown>0) {
                        coundDownTv.setText(countDown+"秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    } else {
                        coundDownTv.setText(countDown+"秒");
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;

                case ONE_TASK_FIISH:
                    if (homeFinsh&&versionFinsh&&advertistFinsh) {
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;
                case ALL_TASK_FIISH:
                    //跳转到主页面
                    Log.d("LQS: ", "所有任务完成");
                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build("/main/MainActivity").withInt("", 1).navigation();
                    //Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    //startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);//把handler发送的消息，这些消息如果没有被处理的话，将会被该函数删除,可以避免内存泄漏
    }
}
