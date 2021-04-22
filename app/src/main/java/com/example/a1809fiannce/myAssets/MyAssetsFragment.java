package com.example.a1809fiannce.myAssets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class MyAssetsFragment extends BaseFragment {
    private ToolBar toolbar;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myassets;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }
}