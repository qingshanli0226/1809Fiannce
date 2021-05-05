package com.example.fiannce.fragment.investfragment.hotfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceNetManager;

public class HotFragment extends BaseFragment {

    private TextView text;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        text = (TextView) mView.findViewById(R.id.text);

        if (FiannceNetManager.getInstance().getNetConnect()){
            text.setText("网络已连接");
        }else {
            text.setText("网络已断开");
        }
    }

    @Override
    public void onConnect() {
        super.onConnect();

        text.setText("网络从未连接变为已连接");
    }

    @Override
    public void DisConnect() {
        super.DisConnect();

        text.setText("网络从已连接变为未连接");
    }
}