package com.example.myfinancial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.framework.BaseActivity;
import com.example.pay.PayActivity;

import org.greenrobot.eventbus.EventBus;

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

    public void startpay(View view) {
        EventBus.getDefault().postSticky("11");
        startActivity(new Intent(this, PayActivity.class));
    }
}