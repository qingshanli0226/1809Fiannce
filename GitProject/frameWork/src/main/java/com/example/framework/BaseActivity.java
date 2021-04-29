package com.example.framework;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.framework.view.LoadPage;
import com.example.framework.view.ToolBar;

import okhttp3.Cache;

public abstract class BaseActivity<P extends  BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarOnClickLisenter, CacheConnectManager.IConnect {
    protected P mPresenter;
    protected ToolBar toolBar;
    protected LoadPage loadPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPage = new LoadPage(this) {
            @Override
            protected int getSuccessLayout() {
                return getLayoutId();
            }
        };
        setContentView(loadPage);
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarOnClickLisenter(this);

        initView();
        initPresenter();
        initData();
        CacheConnectManager.getInstance().registerConnectListener(this);
    }
    
    @LayoutRes
    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initPresenter();


    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisConnect() {

    }

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        CacheConnectManager.getInstance().unRegisterConnectListener(this);
    }
}
