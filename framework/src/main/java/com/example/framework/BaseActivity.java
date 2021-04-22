package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.view.ToolBar;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener{

    protected P httpPresenter;
    protected ToolBar toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        initView();
//        toolBar = findViewById(R.id.toolbar);
//        toolBar.setToolbarListener(this);
        initPresenter();
        initData();

    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter !=null){
            httpPresenter.detachView();
            httpPresenter =null;
        }
    }

    @Override
    public void onLeftImgClick() {

    }

    @Override
    public void onCenterTitleClick() {

    }

    @Override
    public void onRoghtImgClick() {

    }
}
