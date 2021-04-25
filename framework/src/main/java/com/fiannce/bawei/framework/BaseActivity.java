package com.fiannce.bawei.framework;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.R;
import com.fiannce.bawei.framework.view.LoadingPage;

public abstract class BaseActivity<T extends  BasePresenter> extends AppCompatActivity {

    protected T httpPresenter;
    protected boolean isUseLoading = true;
    protected LoadingPage loadingPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLaoutId());

        loadingPage=  new LoadingPage(this){

            @Override
            protected int getSuccessLayoutId() {
                return getLaoutId();
            }
        };

        setContentView(loadingPage);
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
