package com.finance.framework;

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void showError(String msg);
}
