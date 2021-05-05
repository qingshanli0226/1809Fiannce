package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.manager.FiannceNetManager;
import com.example.net.PageView;

public abstract class BaseFragment<T extends BasePresenter>extends Fragment implements FiannceNetManager.NetConnectListener {

    protected T httpPresenter;
    protected View mView;
    protected PageView pageView;
    protected Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = pageView = new PageView(getContext()) {
            @Override
            protected int FindLayout() {
                return getLayoutId();
            }
        };
        toolbar = mView.findViewById(R.id.tob);

        return mView = inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initPresenter();
        initData();
        FiannceNetManager.getInstance().RegisterConnect(this);
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
        if (httpPresenter != null){
            httpPresenter.detachView();
        }

        FiannceNetManager.getInstance().UnRegisterConnect(this);
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void DisConnect() {

    }
}