package com.example.a1809fiannce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.network.BaseFragment;
import com.example.network.TobView;


public class MyMoneyFragment extends BaseFragment {


    @Override
    protected void initData() {
             tobView.ImgCallBackListener(new TobView.ImgCallBack() {
            @Override
            public void OnLeftImg() {

            }

            @Override
            public void OnRightImg() {
                Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int FindLayout1() {
        return R.layout.fragment_my_money;
    }
}