package com.fiannce.bawei.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fiannce.bawei.framework.view.ToolBar;


public abstract class BaseFragmnet<T extends BasePresenter> extends Fragment implements ToolBar.IToolbarListener{
    protected T httpPresenter;
    protected View mView;
    protected ToolBar toolBar;
    public <T extends View> T findViewById(@IdRes int id){
        return mView.findViewById(id);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        toolBar = findViewById(R.id.toolbar);
        if (toolBar !=null) {
            toolBar.setToolbarListener(this);
        }
        initPresenter();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView = inflater.inflate(getLayoutId(),container,false);
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

    public void destroy() {
        if (httpPresenter!=null) {
            httpPresenter.detachView();
        }
    }


}
