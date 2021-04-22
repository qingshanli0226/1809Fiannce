package com.finance.framework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected  T httpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initPresenter();
        initData();
    }

    protected abstract int getLayoutId();
    protected abstract void initPresenter();
    protected abstract void initData();
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }
}