package com.example.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.view.ToolBar;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.user.R;
import com.example.user.login.ILogin;
import com.example.user.login.LoginPresenter;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView, ILogin {

    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText number;
    private android.widget.EditText username;
    private android.widget.EditText password;
    private android.widget.EditText confirmpassword;
    private android.widget.Button register;
    private ProgressBar regProgress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        number = (EditText) findViewById(R.id.reg_number);
        username = (EditText) findViewById(R.id.reg_username);
        password = (EditText) findViewById(R.id.reg_password);
        confirmpassword = (EditText) findViewById(R.id.reg_confirmpassword);
        register = (Button) findViewById(R.id.register);
        regProgress = (ProgressBar) findViewById(R.id.reg_progress);
    }

    @Override
    protected void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!number.getText().toString().equals(null)&&!username.getText().toString().equals(null)&&!password.getText().toString().equals(null)&&!confirmpassword.getText().toString().equals(null)){
                    httpPresenter.getRegister();
                }else {
                    Toast.makeText(RegisterActivity.this, "能不能输入空值", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new RegisterPresenter(this);
    }

    @Override
    public String username() {
        return number.getText().toString().trim();
    }

    @Override
    public String password() {
        return password.getText().toString().trim();
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

            SpUtil.setString(this,loginBean.getResult().getToken());

            FiannceArouter.getInstance().getIAppInterface().openMainActivity(this,null);
        }
    }

    @Override
    public void onRegister(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

            LoginPresenter loginPresenter = new LoginPresenter(this);
            loginPresenter.getLogin();
        }
    }

    @Override
    public void showLoading() {
//        loadingPage.showTransparentLoadingView();
        regProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
//        loadingPage.showSuccessView();
        regProgress.setVisibility(View.GONE);
    }

    @Override
    public void Error(String error) {
        loadingPage.showError(error);
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}