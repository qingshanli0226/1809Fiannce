package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;
    protected View inflate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(getLayoutId(), container, false);
        initView();
        initPrensenter();
        initData();
        return inflate;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initPrensenter();


    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    //清除
    private void destroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
