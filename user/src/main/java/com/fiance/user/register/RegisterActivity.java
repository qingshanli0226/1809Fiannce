package com.fiance.user.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.fiance.framework.BaseActivity;
import com.fiance.framework.MyView.ToolBar;
import com.fiance.net.mode.RegisterBean;
import com.fiance.user.R;
import com.fiance.user.login.LoginActivity;

@Route(path = "/user/RegisterActivity")
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {


    private ToolBar toolbar;
    private EditText username;
    private EditText pwd;
    private EditText twoPwd;
    private TextView btn;
    private RegisterPresenter registerPresenter;
    private SharedPreferences user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter() {
         httpPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void initData() {
        user = getSharedPreferences("user", MODE_PRIVATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEt = username.getText().toString().trim();
                String pwdEt = pwd.getText().toString().trim();
                if (TextUtils.isEmpty(userEt) && TextUtils.isEmpty(pwdEt)){
                    Toast.makeText(RegisterActivity.this, "填写信息不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    httpPresenter.getRegisterData(userEt,pwdEt);
                }
            }
        });

    }
    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        twoPwd = findViewById(R.id.two_pwd);
        btn = findViewById(R.id.btn);
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {
        LogUtils.json(registerBean);
        if (registerBean.getCode().equals("200")){
            SharedPreferences.Editor editor=user.edit();
            editor.putBoolean("isLogin",true);
            editor.putString("user",""+username.getText().toString().trim());
            editor.putString(""+pwd.getText().toString().trim(),"123");
            editor.commit();
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
