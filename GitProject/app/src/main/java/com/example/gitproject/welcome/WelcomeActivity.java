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

import com.example.framework.BaseActivity;
import com.example.gitproject.R;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdateBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeView {


    private TextView welData;
    private ProgressBar welProgress;
    private final int ONE_INDEX = 1;
    private final int ONE_INDEX_TIME = 3;
    private final int All_INDEX = 2;

    @Override
    public int getLayoutid() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {

        welData = (TextView) findViewById(R.id.wel_data);
        welProgress = (ProgressBar) findViewById(R.id.wel_progress);
    }

    @Override
    public void initPresenter() {
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getDataHome();
        mPresenter.getAppUpdate();
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        Toast.makeText(this, "主页数据"+homeBean, Toast.LENGTH_SHORT).show();
        welData.setText(homeBean.toString());
//        handler.sendEmptyMessageDelayed(ONE_INDEX,)
    }

    @Override
    public void onAppUpdate(UpdateBean updateBean) {
        Toast.makeText(this, "版本号"+updateBean, Toast.LENGTH_SHORT).show();
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


        }
    };
}