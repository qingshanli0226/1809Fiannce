package com.example.myfinancial.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.BaseFragment;
import com.example.myfinancial.R;

public class MyMoneyFragment extends BaseFragment {

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {

        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showLoading();
    }

    @Override
    public int getbandLayout() {
        return R.layout.fragment_my_money;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}