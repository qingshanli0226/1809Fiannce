package com.example.network;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;

public abstract   class BaseFragment<P extends BasePresenter> extends Fragment {
    protected View BaseView;
    protected P mPresenter;
    private PageView pageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        BaseView = pageView =new PageView(getContext()) {
            @Override
            protected int FindLayout() {
                return FindLayout1();
            }
        };

        return BaseView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }


    public <V extends View> V FindByID(int id) {
        return BaseView.findViewById(id);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int FindLayout1();



}
