package com.fiannce.bawei.fragment.mineFragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.BasePresenter;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.user.exitLogin.ExitLoginActivity;
import com.fiannce.user.login.LoginActivity;
import com.fiannce.zhaoyuzan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment{


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
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        Intent intent = new Intent(getActivity(), ExitLoginActivity.class);
        startActivity(intent);

    }
}
