package com.example.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.framework.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PayActivity extends BaseActivity {

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void getMessage(String msg){
        if (msg.equals("11")){
            Toast.makeText(this, "获取到了消息", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected int getbandLayout() {
        return R.layout.activity_pay;
    }

    public void aa(View view) {
        startActivity(new Intent("myuseractivity"));
    }
}