package com.example.network;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract   class BaseFragment extends Fragment {
    protected View BaseView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return BaseView=inflater.inflate(FindLayout(),container,false);
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

    protected abstract int FindLayout();



}
