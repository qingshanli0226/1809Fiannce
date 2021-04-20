package com.example.myapplication.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.model.HomeBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView contenNum;
    private ProgressBar bar;
    private TextView homedata;

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();

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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        homedata.setText(""+homeBean.toString());
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息:"+versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
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
}