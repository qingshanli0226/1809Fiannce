package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P httpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initPresenter();
        initData();

    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter !=null){
            httpPresenter.detachView();
            httpPresenter =null;
        }
    }
}