package com.example.frame;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter>extends AppCompatActivity implements CacheconnetManager.IConnect,CacheUserManager.ILoginChange{
    protected T mPresenter;
    protected MyToolbar myToolbar;
    protected LoadingPage loadingPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage=new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        initPresenter();
        initData();
        CacheconnetManager.getInstance().registerConnectListener(this);
        CacheUserManager.getInstance().registerLogin(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        CacheUserManager.getInstance().unregisterLogin(this);
    }

    public void destroy() {
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
    }
}
