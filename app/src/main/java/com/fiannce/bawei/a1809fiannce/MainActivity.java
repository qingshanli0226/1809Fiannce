package com.fiannce.bawei.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.pay.PayActivity;
import com.fiannce.bawei.user.LoginActivity;



@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity {
    private TextView progressTv;

    @Override
    protected int getLayoutId() {
        Log.d("lqs", "");
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        progressTv.setText("主页面: "+homeBean.toString());


    }

    @Override
    protected void initView() {
        progressTv = findViewById(R.id.progressTv);

    }
}
