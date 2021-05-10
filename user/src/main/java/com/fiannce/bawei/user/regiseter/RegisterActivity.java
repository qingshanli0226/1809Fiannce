package com.fiannce.bawei.user.regiseter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.view.ToolBar;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.net.user.register.bean.RegisterBean;
import com.fiannce.bawei.user.R;
import com.fiannce.bawei.user.UserModule;
import com.fiannce.bawei.user.UserPresenter;
import com.fiannce.bawei.user.UserView;

public class RegisterActivity extends BaseActivity<UserPresenter>  implements UserView {


    private android.widget.EditText registerPhone;
    private android.widget.EditText registerName;
    private android.widget.EditText registerPassword;
    private android.widget.EditText registerConfirmPassword;
    private ToolBar toolbar;
    private android.widget.Button register;
    private String name;
    private String password;
    private String phone;
    private String confirm;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserPresenter(this);
    }

    @Override
    protected void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = registerPhone.getText().toString().trim();
                name  = registerName.getText().toString().trim();
                password = registerPassword.getText().toString().trim();
                confirm = registerConfirmPassword.getText().toString().trim();
                if (phone.equals("")&&name.equals("")&&password.equals("")&&confirm.equals("")){
                    Toast.makeText(RegisterActivity.this, "内容有空白", Toast.LENGTH_SHORT).show();
                    return;
                }
                httpPresenter.UserRegister(name,password);
            }
        });
    }

    @Override
    protected void initView() {
        registerPhone = (EditText) findViewById(R.id.register_phone);
        registerName = (EditText) findViewById(R.id.register_name);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerConfirmPassword = (EditText) findViewById(R.id.register_confirm_password);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        register = (Button) findViewById(R.id.register);
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


    @Override
    public void onLoginData(LoginBean loginBean) {

    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {
        if ( registerBean.getCode().equals("200")){
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            bundle.putString("password",password);
            new UserModule().openLoginAcivity(this,bundle);
            finish();
       }
    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPhone = null;
        registerName = null;
        registerPassword = null;
        registerConfirmPassword = null;
        toolbar = null;
        register = null;
    }
}