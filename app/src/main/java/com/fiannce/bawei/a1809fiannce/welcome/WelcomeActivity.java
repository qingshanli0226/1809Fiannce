package com.fiannce.bawei.a1809fiannce.welcome;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView{
    private TextView contentTv;
    private ProgressBar progressBar;

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
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        contentTv.setText(homeBean.toString());

    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息："+ versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
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
}
