package com.example.fiannce.fragment.mymoneyfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.ToolBarView;

public class MyMoneyFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {

    private ToolBarView tobView;
    private TextView user;


    public MyMoneyFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_money;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        if (UserCallBack.getInstance().getName() != null){
            user.setText(UserCallBack.getInstance().getName()+"");
        }
    }

    @Override
    protected void initView() {
        user = (TextView) mView.findViewById(R.id.user);

        FiannceUserManager.getInstance().register(this);

        if (FiannceUserManager.getInstance().getLog()){
            Toast.makeText(getContext(), "当前用户已登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "未登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginChange(boolean isLogin) {
        if (isLogin){
            Toast.makeText(getContext(), "已登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "未登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        FiannceUserManager.getInstance().unRegister(this);
    }
}