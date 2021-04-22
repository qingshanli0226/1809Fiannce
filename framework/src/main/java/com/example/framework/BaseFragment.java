package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.framework.view.ToolBar;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements ToolBar.IToolbarListener {

    protected P httpPresenter;
    private View inflate;
    protected ToolBar toolBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(getLayoutId(), container, false);
        ScreenAdapterTools.getInstance().loadView(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
//        toolBar = findViewById(R.id.toolbar);
//        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    public <T extends View> T findViewById(@IdRes int id) {
        return inflate.findViewById(id);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter !=null){
            httpPresenter.detachView();
            httpPresenter =null;
        }
    }

    @Override
    public void onLeftImgClick() {

    }

    @Override
    public void onCenterTitleClick() {

    }

    @Override
    public void onRoghtImgClick() {

    }
}
