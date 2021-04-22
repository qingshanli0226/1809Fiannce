package com.finance.framework;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected  T httpPresenter;
    protected View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView = inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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