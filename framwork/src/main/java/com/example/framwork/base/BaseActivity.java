package com.example.framwork.base;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framwork.R;
import com.example.framwork.view.PageView;
import com.example.framwork.view.TobView;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    protected PageView pageView;
    protected TobView tobView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageView=new PageView(this) {
            @Override
            protected int FindLayout() {
                return FindLayout1();
            }
        };
        setContentView(pageView);
        tobView=pageView.findViewById(R.id.tob);
        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int FindLayout1();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.delView();
        }
    }
}
