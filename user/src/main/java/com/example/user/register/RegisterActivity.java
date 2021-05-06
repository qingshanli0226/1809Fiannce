package com.example.user.register;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.frame.BaseActivity;
import com.example.frame.CommonConstant;
import com.example.frame.FrameArouter;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;

public class RegisterActivity extends BaseActivity<UserPresenter>implements IUserView {
    private android.widget.EditText etRegisterNumber;
    private android.widget.EditText etRegisterName;
    private android.widget.EditText etRegisterPwd;
    private android.widget.EditText etRegisterPwdagain;
    private android.widget.Button btnRegister;
    private String name,pwd;

    @Override
    protected int getLayoutId() {
        return R.layout.regi;
    }

    @Override
    protected void initPresenter() {
         mPresenter = new UserPresenter(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        etRegisterNumber = findViewById(R.id.et_register_number);
        etRegisterName = findViewById(R.id.et_register_name);
        etRegisterPwd = findViewById(R.id.et_register_pwd);
        etRegisterPwdagain = findViewById(R.id.et_register_pwdagain);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etRegisterNumber.getText().toString().trim();
                 name = etRegisterName.getText().toString().trim();
                 pwd = etRegisterPwd.getText().toString().trim();
                String yespwd = etRegisterPwdagain.getText().toString().trim();

                if(name!=null&&pwd!=null&&yespwd!=null) {

                 if (pwd.equals(yespwd)){
                     mPresenter.getRegister(name, pwd);
                     SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                     SharedPreferences.Editor edit = login.edit();
                     edit.putString("name",name);
                     edit.putBoolean("islogin",true);
                     edit.commit();

                 }else {
                     Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                 }

                }

            }
        });
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisConnect() {

    }

    @Override
    public void onRegister(RegisterBean registerBean) {
       if (registerBean.getCode().equals("200")){
           mPresenter.getLogin(name,pwd);
           FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
       }else {
           Toast.makeText(this, registerBean.getResult(), Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onLogin(LoginBean loginBean) {
       Toast.makeText(this, loginBean.getCode(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

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

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }
}
