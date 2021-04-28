package com.fiannce.bawei.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.fiannce.bawei.common.FiannceConstants;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.FiannceArouter;

public class PayActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.btnOpenLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FiannceArouter.getInstance().getUserInterface().openLoginAcivity(PayActivity.this, null);
                FiannceArouter.getInstance().build(FiannceConstants.LOGIN_PATH).navigation();
            }
        });

    }
}
