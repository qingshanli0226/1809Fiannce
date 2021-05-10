package com.example.user;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.mannager.FiannceUserMannager;
import com.example.net.bean.AutoBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.LogoutBean;
import com.example.net.bean.RegisterBean;
import com.example.user.mvp.MorePresenter;
import com.example.user.mvp.MoreView;

public class LogActivity extends BaseActivity<MorePresenter> implements MoreView {

    private android.widget.EditText name;
    private android.widget.EditText pwd;
    private android.widget.Button login;

    @Override
    public void initPresenter() {
        mPresenter=new MorePresenter(this);
    }

    @Override
    public void initData() {
        login.setOnClickListener(v -> {
            String loginname = name.getText().toString().trim();
            String loginpwd = pwd.getText().toString().trim();
           mPresenter.onLogin(loginname,loginpwd);
        });
    }

    @Override
    public void initView() {
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);
    }

    @Override
    public int getbandLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void initRegister(RegisterBean registerBean) {

    }

    @Override
    public void initLogin(LoginBean loginBean) {
        String code = loginBean.getCode();
        if (code.equals("200")){
            SpUtil.setString(this,loginBean.getResult().getToken());
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            FiannceUserMannager.getInstance().setIsLwogin(true);
            SpUtil.setString(this,loginBean.getResult().getToken());
            finish();
        }else {
            Toast.makeText(this, "用户名或密码错误  请重新输入或注册", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initAuto(AutoBean autoBean) {

    }

    @Override
    public void initLogout(LogoutBean logoutBean) {

    }
}