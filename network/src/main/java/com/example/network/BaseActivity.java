package com.example.network;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(pageView);
        initView();
        initData();

    }
    protected PageView pageView=new PageView(this) {
        @Override
        protected int FindLayout() {
            return FindLayout();
        }
    };
    protected abstract void initData();

    protected abstract void initView();

    protected abstract int FindLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.delView();
        }
    }
}
