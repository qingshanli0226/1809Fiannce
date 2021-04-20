package com.Fiannce.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.net.mode.HomeBean;
@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity {


    private android.widget.TextView progressTv;

    @Override
    protected void initView() {

        progressTv = (TextView) findViewById(R.id.progressTv);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        progressTv.setText("首页"+homeBean.toString());
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_main;
    }


}
