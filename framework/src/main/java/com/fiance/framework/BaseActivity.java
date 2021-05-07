package com.fiance.framework;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fiance.framework.MyView.LoadingPage;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements FiannceConnectManager.IConnectListener{
    protected  T httpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },100);
        }

        initView();
        initPresenter();
        initData();

        FiannceConnectManager.getInstance().registerConnectListener(this);
    }
    protected LoadingPage loadingPage;
    protected abstract int getLayoutId();
    protected abstract void initPresenter();
    protected abstract void initData();
    protected abstract void initView();

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detchView();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        FiannceConnectManager.getInstance().unRegisterConnectListener(this);
    }
    @Override
    public void onConnected() {
    }

    @Override
    public void onDisconnected() {
    }
}