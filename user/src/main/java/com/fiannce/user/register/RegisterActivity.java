package com.fiannce.user.register;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fiannce.commond.CommonConstant;
import com.fiannce.framework.BaseActivity;

import com.fiannce.framework.model.FrameArouter;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.user.R;
import com.fiannce.user.login.LoginActivity;


public class RegisterActivity extends BaseActivity<UserPresenter> implements IUserView {

    private EditText etRegisterNumber;
    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdagain;
    private Button btnRegister;
    private String name;
    private String password;

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
        httpPresenter = new UserPresenter(this);
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断不为空
                name = etRegisterName.getText().toString().trim();
                password = etRegisterPwd.getText().toString().trim();
                String yesPwd = etRegisterPwdagain.getText().toString().trim();

                if(TextUtils.isEmpty(name)|| TextUtils.isEmpty(password)||TextUtils.isEmpty(yesPwd)){
                    Toast.makeText(RegisterActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                } else{
                    //判断两次密码不一样

                    if(password.equals(yesPwd)){
                        //一致
                        httpPresenter.getRegister(name,password);
                    } else{
                        //不一致
                        Toast.makeText(RegisterActivity.this, "两次密码不一样", Toast.LENGTH_SHORT).show();
                    }
                }


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

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @Override
    public void onRegister(RegisterBean registerBean) {
        if(registerBean.getCode().equals("200")){
            //跳转登录页面
//            FrameArouter.getInstance().build(CommonConstant.USER_LOGIN_PATH).navigation();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("password",password);
            startActivity(intent);
        } else{
            Toast.makeText(this, ""+registerBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

    }
}
