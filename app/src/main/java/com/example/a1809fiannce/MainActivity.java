package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.mode.HomeBean;




@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity {
    private TextView progressTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();



    }

    @Override
    protected void initView() {
        progressTv = findViewById(R.id.progressTv);

    }
}
