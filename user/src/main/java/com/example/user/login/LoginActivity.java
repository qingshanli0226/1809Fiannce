package com.example.user.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.user.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.model.LoginBean;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    private ImageView back;
    private EditText phone;
    private EditText pass;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();



    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLaoutId() {
        return 0;
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
        return null;
    }

    @Override
    public String password() {
        return null;
    }

    @Override
    public void onLoginData(LoginBean loginBean) {

    }
}
