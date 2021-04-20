package com.example.myapplication.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.BaseActivity;
import com.example.model.HomeBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}