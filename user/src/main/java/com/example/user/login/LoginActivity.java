package com.example.user.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.mode.LoginBean;
import com.example.user.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILogin {
    private EditText loginNumber;
    private EditText loginPassword;
    private Button login;
    private ProgressBar loginProgress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginNumber = (EditText) findViewById(R.id.login_number);
        loginPassword = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
        loginProgress = (ProgressBar) findViewById(R.id.login_progress);
    }

    @Override
    protected void initData() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpPresenter.getLogin();
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new LoginPresenter(this);
    }

    @Override
    public String username() {
        return loginNumber.getText().toString().trim();
    }

    @Override
    public String password() {
        return loginPassword.getText().toString().trim();
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")){
            FiannceUserManager.getInstance().setLoginBean(loginBean);

            SpUtil.setString(this,loginBean.getResult().getToken());

            FiannceArouter.getInstance().getIAppInterface().openMainActivity(this,null);
        }
    }

    @Override
    public void showLoading() {
        loginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loginProgress.setVisibility(View.GONE);
    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
