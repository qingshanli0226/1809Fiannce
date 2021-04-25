package com.fiannce.bawei.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fiannce.bawei.framework.view.LoadingPage;


public abstract class BaseFragmnet<T extends BasePresenter> extends Fragment {
    protected T httpPresenter;
    protected View mView;

    protected LoadingPage loadingPage;
    public <T extends View> T findViewById(@IdRes int id) {
        return mView.findViewById(id);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = loadingPage = new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        initView();
        initPresenter();
        initData();
        return mView;
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
