package com.example.a1809zg.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1809zg.R;
import com.example.frame.BaseActivity;
import com.example.net.bean.LoginBean;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {


    private android.widget.EditText name;
    private android.widget.EditText password;
    private android.widget.Button btnLogin;

    @Override
    public void Login(LoginBean loginBean) {
        String code = loginBean.getCode();
        String message = loginBean.getMessage();
       if (code.equals("200")){
           Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        mPresenter=new LoginPresenter(this);
    }

    @Override
    protected void initView() {

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = name.getText().toString();
                String s1 = password.getText().toString();
                if (!s.equals("")&&!s1.equals("")){
                    mPresenter.LoginData(s,s1);
                }else {
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}