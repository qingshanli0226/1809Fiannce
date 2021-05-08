package com.fiannce.bawei.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiannce.bawei.framework.view.ToolBar;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener  {

    protected T httpPresenter;
//    protected  LoadingPage loadingPage;
    protected ToolBar toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        initView();
        toolBar =(ToolBar)findViewById(R.id.toolbar);
        if (toolBar !=null){
            toolBar.setToolbarListener(this);
        }
        initPresenter();
        initData();
    }

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
}
