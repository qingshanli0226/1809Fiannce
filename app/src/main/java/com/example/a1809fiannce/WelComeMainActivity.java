package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.a1809fiannce.update.UpdatePresenter;
import com.example.formwork.model.HomeBean;
import com.example.formwork.model.UpdateBean;
import com.example.network.BaseActivity;

public class WelComeMainActivity extends BaseActivity<UpdatePresenter> implements CallBack{


    @Override
    protected void initData() {
            mPresenter=new UpdatePresenter(this);
            mPresenter.HomeData();
            mPresenter.UpdateData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int FindLayout() {
        return R.layout.activity_wel_come_main;
    }

    @Override
    public void HomeData(HomeBean homeBean) {
        Log.i("zx", "HomeData: "+homeBean.toString());
    }

    @Override
    public void UpdateData(UpdateBean updateBean) {
        Log.i("zx", "UpdateData: "+updateBean.toString());
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {

    }
}