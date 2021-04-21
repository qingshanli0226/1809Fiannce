package com.example.gitproject.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.gitproject.R;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdateBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeView {


    private TextView welData;
    private ProgressBar welProgress;
    private final int ONE_TASK_FIISH = 0;
    private final int COUNT_TIME = 2;
    private boolean task_one = false;
    private boolean task_two = false;
    private boolean task_three = false;
    private int count = 3;
    private TextView countDown;


    @Override
    public int getLayoutid() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        welData = (TextView) findViewById(R.id.wel_data);
        welProgress = (ProgressBar) findViewById(R.id.wel_progress);
        countDown = (TextView) findViewById(R.id.countDown);
    }

    @Override
    public void initPresenter() {
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getDataHome();
        mPresenter.getAppUpdate();
        handler.sendEmptyMessageDelayed(COUNT_TIME,1000);

    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        welData.setText(homeBean.toString());
        task_two = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onAppUpdate(UpdateBean updateBean) {
        Toast.makeText(this, "版本号"+updateBean, Toast.LENGTH_SHORT).show();
        task_three = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);

    }

    @Override
    public void showLoading() {
        welProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        welProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "错误:"+error, Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == COUNT_TIME){
                count--;
                if(count < 0 ){
                    task_one = true;
                    handler.sendEmptyMessage(ONE_TASK_FIISH);
                } else{
                    countDown.setText(count+"s");
                    handler.sendEmptyMessageDelayed(COUNT_TIME,1000);
                }
            } else if(msg.what == ONE_TASK_FIISH){
                if(task_one && task_two && task_three){
                    //跳转
                    ARouter.getInstance().build("/app/MainActivity").navigation();
                }
            }

        }
    };

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }
}