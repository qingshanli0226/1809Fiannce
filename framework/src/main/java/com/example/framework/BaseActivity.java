package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.FiannceConnectManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener, FiannceConnectManager.IConnectListener {

    protected T httpPresenter;
    protected LoadingPage loadingPage;
    protected ToolBar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        FiannceConnectManager.getInstance().registerConnectListener(this);

        loadingPage=new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        toolBar=findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();

        FiannceConnectManager.getInstance().unRegisterConnectListener(this);
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }
}
