package com.fiannce.bawei.framework;

import android.os.Bundle;
import android.widget.Toast;

import com.fiannce.bawei.framework.view.LoadingPage;
import com.fiannce.bawei.framework.view.ToolBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener{

    protected T httpPresenter;
    protected ToolBar toolBar;
    protected boolean isUseLoading = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
    }

    protected LoadingPage loadingPage;

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter!=null) {
            httpPresenter.detachView();
        }
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightImgClick() {
    }

    @Override
    public void onRightTvClick() {

    }
}
