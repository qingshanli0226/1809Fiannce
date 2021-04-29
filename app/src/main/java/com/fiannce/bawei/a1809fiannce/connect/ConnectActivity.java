package com.fiannce.bawei.a1809fiannce.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.FiannceConnectManager;

public class ConnectActivity extends BaseActivity {
    private TextView connctStatusTv;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_connect;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        if (FiannceConnectManager.getInstance().isConnected()) {
            connctStatusTv.setText("系统已经连接网络");
        } else {
            connctStatusTv.setText("系统没有连接网络");
        }

    }

    @Override
    protected void initView() {
        connctStatusTv = findViewById(R.id.connectStatusTv);
    }

    @Override
    public void onConnected() {
        connctStatusTv.setText("系统从未连接变为已经连接网络");
    }

    @Override
    public void onDisconnected() {
        connctStatusTv.setText("系统从已连接变为未连接网络");
    }
}
