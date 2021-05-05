package com.example.framework;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.FiannceConnectManager;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener, FiannceConnectManager.IConnectListener, FiannceUserManager.IUserLoginChanged {

    protected T httpPresenter;
    protected ToolBar toolBar;
    private boolean isUseLoading = true;
    protected LoadingPage loadingPage;

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
        //toolBar.setToolbarListener(this);
        initPresenter();
        initData();

        //页面启动时，注册网络回调接口
        FiannceConnectManager.getInstance().registerConnectListenter(this);

        FiannceUserManager.getInstance().register(this);

    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        FiannceConnectManager.getInstance().unRegisterConnectListenter(this);
        FiannceUserManager.getInstance().unRegister(this);
    }

    public void destroy(){
        if (httpPresenter!=null){
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

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onLoginChange(boolean isLogin) {

    }
}
