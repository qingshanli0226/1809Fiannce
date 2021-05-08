package com.example.user.register;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.net.BuildConfig;
import com.example.user.R;
import com.example.user.login.ILoginView;
import com.example.user.login.LoginPresenter;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.model.LoginBean;
import com.fiannce.bawei.net.model.RegisterBean;

import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView, ILoginView {

    private ImageView back;
    private EditText phone;
    private EditText name;
    private EditText pass;
    private EditText unpaw;
    private Button register;
    private RegisterPresenter registerPresenter;

    @Override
    protected void initData() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "" + phone.getText().toString(), Toast.LENGTH_SHORT).show();
                String sss = "^[A-Za-z0-9]{2,52}$";

                if (Pattern.matches(sss, phone.getText().toString()) && Pattern.matches(sss, name.getText().toString()) && Pattern.matches(sss, pass.getText().toString()) && Pattern.matches(sss, unpaw.getText().toString())) {

                    registerPresenter.getRegisterData();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(RegisterActivity.this, "填写信息不能为空", Toast.LENGTH_SHORT).show();


                }


            }
        });


    }

    @Override
    protected void initPresenter() {
        registerPresenter = new RegisterPresenter(this);
    }

    @Override
    protected int getLaoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.back);
        phone = (EditText) findViewById(R.id.phone);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        unpaw = (EditText) findViewById(R.id.unpaw);
        register = (Button) findViewById(R.id.register);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    @Override
    public String name() {
        return name.getText().toString().trim();
    }

    @Override
    public String password() {
        return pass.getText().toString().trim();
    }

    @Override
    public void onLoginData(LoginBean loginBean) {

    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {
        if (BuildConfig.DEBUG) Log.d("RegisterActivity", "registerBean:" + registerBean);
        if (registerBean.getCode().equals("200")) {

            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
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
    public void onConnected() {
        super.onConnected();
        Toast.makeText(this, "有网", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        super.onDisconnected();
        Toast.makeText(this, "你猜有网没", Toast.LENGTH_SHORT).show();
    }
}
