package com.example.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo.Demo;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.model.LoginBean;
import com.example.user.R;

@Route(path = Demo.AROUTE_PATH_LOGIN)
public class PersonLoginActivity extends BaseActivity<PersonLoginPresenter> implements IPersonLoginView{

    private com.example.framework.view.ToolBar toolbar;
    private android.widget.EditText loginName;
    private android.widget.EditText loginPwd;
    private android.widget.Button login;
    private String name;
    private String pwd;

    @Override
    protected void initData() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 name = loginName.getText().toString().trim();
                 pwd = loginPwd.getText().toString().trim();
                 httpPresenter.postLogin(name,pwd);

            }
        });

        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
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
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new PersonLoginPresenter(this);
    }

    @Override
    protected void initView() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        loginName = (EditText) findViewById(R.id.login_name);
        loginPwd = (EditText) findViewById(R.id.login_pwd);
        login = (Button) findViewById(R.id.login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_login;
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            LoginBean.ResultBean result = loginBean.getResult();
            String token = result.getToken();

            SharedPreferences login = getSharedPreferences("login", 0);
            SharedPreferences.Editor edit = login.edit();
            edit.putBoolean("is_login",true);
            edit.putString("username",name);
            edit.putString("token",token);
            edit.commit();
            finish();




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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }
}