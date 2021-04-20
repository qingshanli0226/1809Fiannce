package com.example.framework;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
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
    
    @LayoutRes
    public abstract int getLayoutid();

    public abstract void initView();

    public abstract void initPresenter();


    public abstract void initData();





}
