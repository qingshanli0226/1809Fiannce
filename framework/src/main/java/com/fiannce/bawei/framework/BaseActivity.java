package com.fiannce.bawei.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends  BasePresenter> extends AppCompatActivity {

    protected T httpPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLaoutId());
        initView();
        initPresenter();
        initData();


    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract int getLaoutId();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpPresenter != null){
            httpPresenter.detachView();
        }
    }
}
