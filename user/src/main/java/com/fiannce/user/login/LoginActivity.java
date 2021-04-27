package com.fiannce.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.user.R;
import com.fiannce.user.register.RegisterActivity;
import com.fiannce.user.register.RegisterPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    private com.fiannce.framework.view.ToolBar toolbar;
    private android.widget.EditText etLoginNumber;
    private android.widget.EditText etLoginPwd;
    private android.widget.Button btnLogin;

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etLoginNumber.getText().toString().trim();
                String password = etLoginPwd.getText().toString().trim();

                httpPresenter.getLoginData(name,password);
            }
        });
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        etLoginNumber = findViewById(R.id.et_login_number);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")){
            ARouter.getInstance().build("/main/MainActivity").navigation();
//            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
//            startActivity(intent);
        }else {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
