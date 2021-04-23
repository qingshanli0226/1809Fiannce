package com.finance.framework;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance.framework.view.LoadingPage;
import com.finance.framework.view.ToolBar;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected  T httpPresenter;
    protected View mView;
    private ToolBar toolBar;
    protected LoadingPage loadingPage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = loadingPage = new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolBar = mView.findViewById(R.id.toolbar);
        initView();
        initPresenter();
        initData();
    }


    protected abstract int getLayoutId();
    protected abstract void initPresenter();
    protected abstract void initData();
    protected abstract void initView();

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

}