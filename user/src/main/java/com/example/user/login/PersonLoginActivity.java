package com.example.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo.Demo;
import com.example.framework.BaseActivity;
import com.example.model.LoginBean;
import com.example.user.R;

@Route(path = Demo.AROUTE_PATH_LOGIN)
public class PersonLoginActivity extends BaseActivity<PersonLoginPresenter> implements IPersonLoginView{

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new PersonLoginPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}