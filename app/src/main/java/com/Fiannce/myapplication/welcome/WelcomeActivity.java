package com.Fiannce.myapplication.welcome;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Fiannce.myapplication.R;
import com.fiannce.framework.BaseActivity;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView contentTv;
    private ProgressBar progressBar;

    @Override
    protected void initView() {
        contentTv = (TextView) findViewById(R.id.contentTv);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
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
        return R.layout.activity_main;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        contentTv.setText(homeBean+"");
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息：" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
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
}
