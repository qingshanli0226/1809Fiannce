package com.fiannce.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        initView();
        initPresenter();
        initData();
    }

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract int getLayoutid();


}
