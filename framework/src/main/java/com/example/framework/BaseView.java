package com.example.framework;

public interface BaseView {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void initPresenter();

    void initData();

    void initView();

    int getbandLayout();
}
