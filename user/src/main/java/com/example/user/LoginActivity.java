package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.framework.BaseActivity;

public class LoginActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        Log.i("TAG", "initView: 123");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}