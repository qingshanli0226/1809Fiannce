package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getbandLayout());
        initView();
        initData();
        initPresenter();
    }

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView() ;

    protected abstract int getbandLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

     public void destroy(){
        if (mPresenter!=null){

        }
     }
}
