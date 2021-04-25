package com.fiannce.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiannce.framework.view.LoadingPage;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T httppresenter;
    protected LoadingPage loadingPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutid();
            }
        };

        setContentView(loadingPage);
        initView();
        initPresenter();
        initData();
    }

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract int getLayoutid();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destory();
    }

    public void destory() {
        if (httppresenter != null) {
            httppresenter.detachView();
        }
    }

}
