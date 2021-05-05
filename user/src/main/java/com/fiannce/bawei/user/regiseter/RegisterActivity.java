package com.fiannce.bawei.user.regiseter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.view.ToolBar;
import com.fiannce.bawei.user.R;

public class RegisterActivity extends BaseActivity implements ToolBar.IToolbarListener {


    private android.widget.EditText registerPhone;
    private android.widget.EditText registerName;
    private android.widget.EditText registerPassword;
    private android.widget.EditText registerConfirmPassword;
    private com.fiannce.bawei.framework.view.ToolBar registerToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        registerPhone = (EditText) findViewById(R.id.register_phone);
        registerName = (EditText) findViewById(R.id.register_name);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerConfirmPassword = (EditText) findViewById(R.id.register_confirm_password);
        registerToolbar = (ToolBar) findViewById(R.id.register_toolbar);
        registerToolbar.setToolbarListener(this);
    }







    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }

}