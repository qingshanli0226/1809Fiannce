package com.example.framework;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getbandLayout());
        initView();
        initPresenter();
        initData();

    }

     public void initPresenter(){};

    public void initData(){};

    public void initView() {};

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public void showError(String error) {
        Log.d("BaseActivity", error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

     public void destroy(){
        if (mPresenter!=null){
            mPresenter.destroy();
        }
     }
}
