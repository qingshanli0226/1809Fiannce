package com.example.myfinancial.mymoney;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseFragment;
import com.example.myfinancial.R;

public class MyMoneyFragment extends BaseFragment {

    private ImageView headPortrait;
    private TextView loginName;

    @Override
    public int getbandLayout() {
        return R.layout.fragment_my_money;
    }

    @Override
    public void initView() {
        headPortrait = (ImageView) findViewById(R.id.headPortrait);
        loginName = (TextView) findViewById(R.id.loginName);
    }

    @Override
    public void initData() {

    }

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
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}