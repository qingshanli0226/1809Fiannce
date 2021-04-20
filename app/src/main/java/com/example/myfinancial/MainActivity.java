package com.example.myfinancial;


import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.pay.PayActivity;

import org.greenrobot.eventbus.EventBus;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getbandLayout() {
        return R.layout.activity_main;
    }


}