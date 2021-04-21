package com.example.myapplication.fragment;

import androidx.appcompat.widget.Toolbar;

import com.example.framework.BaseFragment;
import com.example.myapplication.R;

public class MymoneyFragment extends BaseFragment {


    private Toolbar myMoneyToobar;

    @Override
    protected void initData() {

        myMoneyToobar.inflateMenu(R.menu.menu);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        myMoneyToobar = (Toolbar) mView.findViewById(R.id.my_money_toobar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mymoney;
    }
}