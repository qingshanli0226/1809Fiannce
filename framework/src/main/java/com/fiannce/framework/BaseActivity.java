package com.fiannce.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiannce.framework.view.LoadingPage;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T httpPresenter;
    protected boolean isUseLoading = true;
    protected LoadingPage loadingPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        initPresenter();
        initData();
    }

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter != null){
            httpPresenter.detachView();
        }
    }
}
