package com.fiannce.bawei.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fiannce.bawei.common.FiannceConstants;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.FiannceArouter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.btnMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceArouter.getInstance().getAppInterface().onEvent("登录成功");
                FiannceArouter.getInstance().build(FiannceConstants.Main_PATH).navigation();
                //FiannceArouter.getInstance().getAppInterface().openMainActivity(LoginActivity.this,null);
            }
        });

    }
}
