package com.example.user.login;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.user.R;

public class LoginActivity extends BaseActivity<LoginPresneter> implements ILoginView{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresneter(this);
    }

    @Override
    protected void initView() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void getLoginData(LoginBean loginBean) {

    }
}