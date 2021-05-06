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
}
