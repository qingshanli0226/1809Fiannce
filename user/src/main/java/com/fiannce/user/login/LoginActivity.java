package com.fiannce.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.user.R;

@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseActivity {


    private com.fiannce.framework.view.ToolBar toobarFake;
    private android.widget.EditText e1;
    private android.widget.EditText e3;
    private android.widget.Button loginButton;

    @Override
    protected void initView() {

        toobarFake = (ToolBar) findViewById(R.id.toobar_fake);
        e1 = (EditText) findViewById(R.id.e1);
        e3 = (EditText) findViewById(R.id.e3);
        loginButton = (Button) findViewById(R.id.login_button);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_login;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}
