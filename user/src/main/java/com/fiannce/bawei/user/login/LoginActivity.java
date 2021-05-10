package com.fiannce.bawei.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.framework.view.ToolBar;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.net.user.register.bean.RegisterBean;
import com.fiannce.bawei.user.R;
import com.fiannce.bawei.user.UserPresenter;
import com.fiannce.bawei.user.UserView;

public class LoginActivity extends BaseActivity<UserPresenter> implements UserView ,FiannceUserManager.IUserLoginChanged{

    private com.fiannce.bawei.framework.view.ToolBar toolbar;
    private android.widget.EditText loginName;
    private android.widget.EditText loginPassword;
    private android.widget.Button login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserPresenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();

        Bundle param = intent.getBundleExtra("param");

        String name = param.getString("name");
        String password = param.getString("password");
        Toast.makeText(this, "注册登录"+name+password, Toast.LENGTH_SHORT).show();
        if (name!=null&&password!=null){
            httpPresenter.UserLogin(name,password);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginname = loginName.getText().toString().trim();
                String loginpassword = loginPassword.getText().toString().trim();
                if (loginname.equals("")&&loginpassword.equals("")){
                    Toast.makeText(LoginActivity.this, "账号密码不得为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                httpPresenter.UserLogin(loginname,loginpassword);
            }
        });
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        loginName = (EditText) findViewById(R.id.login_name);
        loginPassword = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
        FiannceUserManager.getInstance().register(this);

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
        if (loginBean.getCode().equals("200")){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            FiannceUserManager.getInstance().setLogin(loginBean);
//            FiannceUserManager.getInstance().setLogin(true);
            finish();
        }
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {

    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

    }

    @Override
    public void onLoginChange(LoginBean isLogin) {

    }
}