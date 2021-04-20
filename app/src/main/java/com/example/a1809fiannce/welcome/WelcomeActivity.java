package com.example.a1809fiannce.welcome;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.R;
import com.example.framework.BaseActivity;
import com.example.net.mode.HomeBean;
import com.example.net.mode.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {
    private TextView text;
    private ProgressBar pro;

    @Override
    public void onHomeData(HomeBean homeBean) {
        text.setText(homeBean.toString());
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "版本信息："+versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        text = (TextView) findViewById(R.id.text);
        pro = (ProgressBar) findViewById(R.id.pro);
    }

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new WelcomePresenter(this);
    }

    @Override
    public void showLoading() {
        pro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pro.setVisibility(View.GONE);
    }

    @Override
    public void Error(String error) {
        LogUtils.d(error);
        Toast.makeText(this, "请求错误："+error, Toast.LENGTH_SHORT).show();
    }
}