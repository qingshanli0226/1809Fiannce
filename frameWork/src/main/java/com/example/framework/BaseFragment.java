package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.manager.FinanceConnectManager;
import com.example.framework.manager.FinanceUserManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;

public abstract class BaseFragment <T extends BasePresenter> extends Fragment implements ToolBar.IToolbarListener, FinanceConnectManager.IConnectListener, FinanceUserManager.IUserLoginChanged{

    protected T httpPresenter;
    protected View mView;
    protected LoadingPage loadingPage;
    protected ToolBar toolBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = loadingPage = new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };

//        mView = inflater.inflate(getLayoutId(), container, false);
        initView();
        toolBar = mView.findViewById(R.id.toolbar);
        //toolBar.setToolbarListener(this);
        initPresenter();
        initData();

        FinanceConnectManager.getInstance().registerConnectListenter(this);
        FinanceUserManager.getInstance().unRegister(this);
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
        FinanceConnectManager.getInstance().unRegisterConnectListenter(this);
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

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

    @Override
    public void onLoginChange(boolean isLogin) {

    }
}
