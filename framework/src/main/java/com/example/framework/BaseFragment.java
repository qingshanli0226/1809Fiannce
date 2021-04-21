package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected View baseView;
    protected P mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return baseView= inflater.inflate(getbandLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
        initData();
    }

    public <T extends View>T findViewById(int setId){
        return baseView.findViewById(setId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }
    public void destroy(){
        if (mPresenter!=null){
            mPresenter.destroy();
        }
    }
}

