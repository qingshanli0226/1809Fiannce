package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.myview.LoadingPage;
import com.example.framework.myview.ToolBar;

import org.xmlpull.v1.XmlPullParser;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView, ToolBar.IToolbarListener {

    protected View baseView;
    protected P mPresenter;
    protected ToolBar toolBar;
    protected boolean isUserLoading = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView= loadingPage = new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLayoutId() {
                return getbandLayout();
            }
        };
        return baseView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
    }

    protected LoadingPage loadingPage;


    public <T extends View> T findViewById(int setId) {
        return baseView.findViewById(setId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }


}

