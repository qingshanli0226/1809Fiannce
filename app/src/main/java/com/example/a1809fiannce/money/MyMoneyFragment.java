package com.example.a1809fiannce.money;

import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.framwork.base.BaseFragment;
import com.example.framwork.view.TobView;


public class MyMoneyFragment extends BaseFragment {


    @Override
    protected void initData() {
             tobView.setImgCallBackListener(new TobView.iImgCallBack() {
                 @Override
                 public void OnLeftImgListener() {
                     Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
                 }

                 @Override
                 public void OnRightImgListener() {
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