package com.fiance.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fiance.framework.MyView.LoadingPage;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected  T httpPresenter;
    protected View mbaseView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
        initData();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mbaseView= loadingPage = new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        return mbaseView;
    }

    protected LoadingPage loadingPage;
    protected abstract int getLayoutId();
    protected abstract void initPresenter();
    protected abstract void initData();
    protected abstract void initView();


    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detchView();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

}
