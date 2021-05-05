package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.manager.FiannceConnectManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IFragment, ToolBar.IToolbarListener, FiannceConnectManager.IConnectListener {
    protected T httpPresenter;
    protected View mView;
    protected ToolBar toolBar;
    protected LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=loadingPage=new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        ScreenAdapterTools.getInstance().loadView(mView);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        toolBar=mView.findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }

    @Override
    public <T extends View> T findViewById(int resId) {
        return mView.findViewById(resId);
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
