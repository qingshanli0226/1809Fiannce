package com.fiannce.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.user.R;
import com.fiannce.user.register.RegisterActivity;

@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {


    private com.fiannce.framework.view.ToolBar toobarFake;
    private android.widget.EditText e1;
    private android.widget.EditText e3;
    private android.widget.Button loginButton;

    @Override
    protected void initView() {

        toobarFake = (ToolBar) findViewById(R.id.toobar_fake);
        e1 = (EditText) findViewById(R.id.e1);
        e3 = (EditText) findViewById(R.id.e3);
        loginButton = (Button) findViewById(R.id.login_button);
        toobarFake.setToolbarListener(this);
    }

    @Override
    protected void initPresenter() {
        httppresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    httppresenter.getLogin(e1.getText().toString(),e3.getText().toString());
            }
        });
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_login;
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
    public void getLogin(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")){

            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
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
