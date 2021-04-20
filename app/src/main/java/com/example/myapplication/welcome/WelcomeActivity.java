package com.example.myapplication.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.model.HomeBean;
import com.example.model.VersionBean;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView contenNum;
    private ProgressBar bar;
    private TextView homedata;
    private final int ONE_TASK_FINISH = 0;
    private final int All_TASK_FINISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1000;
    private boolean home_finish = false;
    private boolean version_finish = false;
    private boolean advertistFinsh  = false;
    private int session = 3;


    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();
        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initView() {

        contenNum = (TextView) findViewById(R.id.conten_num);
        bar = (ProgressBar) findViewById(R.id.bar);
        homedata = (TextView) findViewById(R.id.homedata);
        contenNum.setText(session+"秒");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        homedata.setText(""+homeBean.toString());
        home_finish = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);

    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息:"+versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        version_finish = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void showLoading() {
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        bar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "请求出现错误:"+error, Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    session--;
                    if (session>0){
                        contenNum.setText(session+"秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    }else {
                        contenNum.setText(session+"秒");
                        advertistFinsh=true;
                        handler.sendEmptyMessage(ONE_TASK_FINISH);
                    }
                    break;
                case ONE_TASK_FINISH:
                    if (home_finish==true && version_finish==true && advertistFinsh==true){
                        handler.sendEmptyMessage(All_TASK_FINISH);
                    }
                    break;
                case All_TASK_FINISH:
                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build("/app/MainActivity").navigation();
//                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}