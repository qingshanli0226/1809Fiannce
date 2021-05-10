package com.example.user.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.user.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.SpUtil;
import com.fiannce.bawei.framework.manager.FiannceArouter;
import com.fiannce.bawei.framework.manager.FiannceConnectManager;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.net.model.LoginBean;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    private ImageView back;
    private EditText phone;
    private EditText pass;
    private Button register;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();



    }

    @Override
    protected void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.getLoginData();
            }
        });
    }

    @Override
    protected void initPresenter() {
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected int getLaoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.back);
        phone = (EditText) findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.pass);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginPresenter.getLoginData();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public String name() {
        return phone.getText().toString().trim();
    }

    @Override
    public String password() {
        return pass.getText().toString().trim();
    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")){
            SpUtil.setString(this,loginBean.getResult().getToken());
            FiannceUserManager.getInstance().setLoginBean(loginBean);

            FiannceArouter.getInstance().getIAppInterface().openMainActivity(this,null);

            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }

    @Override
    public void onConnected() {
        super.onConnected();
        Toast.makeText(this, "有网", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        super.onDisconnected();
        Toast.makeText(this, "没网", Toast.LENGTH_SHORT).show();
    }
}
