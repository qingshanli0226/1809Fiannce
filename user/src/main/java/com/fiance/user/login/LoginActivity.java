package com.fiance.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.fiance.framework.BaseActivity;
import com.fiance.net.mode.LoginBean;
import com.fiance.user.R;

import org.greenrobot.eventbus.EventBus;

@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {


    private com.fiance.framework.MyView.ToolBar toolbar;
    private android.widget.EditText username;
    private android.widget.EditText pwd;
    private android.widget.TextView btn;
    private String userEt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {
         httpPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
              userEt = username.getText().toString().trim();
             String pwdEt = pwd.getText().toString().trim();
             if (userEt==null || pwdEt==null){
                 Toast.makeText(LoginActivity.this, "填写信息不能为空", Toast.LENGTH_SHORT).show();
             }else{
                 httpPresenter.getLoginData(userEt,pwdEt);
             }
         }
     });
    }
    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        btn = findViewById(R.id.btn);
    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        LogUtils.json(loginBean);
        if (loginBean.getCode().equals("200")){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            SharedPreferences sflogin = getSharedPreferences("sflogin", MODE_PRIVATE);
            SharedPreferences.Editor edit = sflogin.edit();
            edit.putString("username",userEt);
            edit.putBoolean("islogin",true);
            edit.commit();
            ARouter.getInstance().build("/main/MainActivity").withInt("", 1).navigation();
        }else{
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
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
