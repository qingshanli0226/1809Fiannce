package com.example.myapplication.fragment;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.myapplication.R;

public class MymoneyFragment extends BaseFragment {


    @Override
    protected void initData() {



    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mymoney;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {
        Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightTvClick() {

    }
}