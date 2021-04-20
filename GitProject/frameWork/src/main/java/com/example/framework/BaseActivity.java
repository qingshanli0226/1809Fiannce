package com.example.framework;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends  BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    private void destroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
