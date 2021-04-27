package com.fiannce.user.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.BasePresenter;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.user.R;
import com.fiannce.user.login.LoginActivity;


public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {

    private EditText etRegisterNumber;
    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdagain;
    private Button btnRegister;
    private String name;
    private String password;

    @Override
    public void onRegisterData(RegisterBean registerBean) {
        Toast.makeText(this, "" + registerBean.toString(), Toast.LENGTH_SHORT).show();
        if (registerBean.getCode().equals("200")){
//            ARouter.getInstance().build("/main/MainActivity").navigation();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
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
    public void showToast(String msg) {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etRegisterName.getText().toString().trim();
                password = etRegisterPwd.getText().toString().trim();

                httpPresenter.getRegisterData(name,password);

            }
        });

    }

    @Override
    protected void initView() {
        etRegisterNumber = findViewById(R.id.et_register_number);
        etRegisterName = findViewById(R.id.et_register_name);
        etRegisterPwd = findViewById(R.id.et_register_pwd);
        etRegisterPwdagain = findViewById(R.id.et_register_pwdagain);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
