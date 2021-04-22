package com.example.framework;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.view.ToolBar;

public abstract class BaseActivity<P extends  BasePresenter> extends AppCompatActivity implements ToolBar.ToolbarOnClickLisenter {
    protected P mPresenter;
    protected ToolBar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarOnClickLisenter(this);
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

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
