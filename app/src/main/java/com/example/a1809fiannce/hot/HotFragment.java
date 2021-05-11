package com.example.a1809fiannce.hot;

import android.widget.TextView;

import com.example.a1809fiannce.R;
import com.example.framwork.base.BaseFragment;
import com.example.framwork.call.FiannceNetManager;


public class HotFragment extends BaseFragment {
    private TextView hotText;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        hotText = baseView.findViewById(R.id.hot_text);
        if (FiannceNetManager.getInstance().getNetConnect()){
            hotText.setText("网络已连接");
        }else {
            hotText.setText("网络已断开连接");
        }

    }

    @Override
    protected int FindLayout1() {
        return R.layout.fragment_hot;
    }

    @Override
    public void OnConnect() {
        hotText.setText("网络从未连接变为已连接");
    }

    @Override
    public void DisConnect() {
        hotText.setText("网络从已连接变为未连接");
    }

}