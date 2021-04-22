package com.example.framework;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.view.ToolBar;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements ToolBar.ToolbarOnClickLisenter {
    protected P mPresenter;
    protected View inflate;
    protected ImageView load;
    protected AnimationDrawable animationDrawable;
    protected ToolBar toolBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(getLayoutId(), container, false);
        initView();
        load = inflate.findViewById(R.id.load);
        animationDrawable = (AnimationDrawable) load.getBackground();
        toolBar = inflate.findViewById(R.id.toolbar);
        toolBar.setToolbarOnClickLisenter(this);
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
