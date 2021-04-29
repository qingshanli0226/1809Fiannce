package com.example.pay;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.framework.mannager.FiannceArote;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PayActivity extends BaseActivity {

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
//        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void getMessage(String msg){
        if (msg.equals("11")){
            Toast.makeText(this, "获取到了消息", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getbandLayout() {
        return R.layout.activity_pay;
    }

    public void aa(View view) {
//        startActivity(new Intent("myuseractivity"));
        FiannceArote.getInstance().getUserInterface().openLoginActivity(PayActivity.this,null);
    }
}