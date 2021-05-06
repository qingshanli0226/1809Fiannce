package com.example.user.login;

import android.view.View;
import android.widget.Toast;

import com.example.frame.BaseActivity;
import com.example.frame.CacheUserManager;
import com.example.frame.SpUtil;
import com.example.net.bean.LoginBean;
import com.example.user.R;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {


    private android.widget.EditText name;
    private android.widget.EditText password;
    private android.widget.Button btnLogin;

    @Override
    public void Login(LoginBean loginBean) {
        String code = loginBean.getCode();
        String message = loginBean.getMessage();
       if (code.equals("200")){
           Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
           SpUtil.putString(this,"token",loginBean.getResult().getToken());
           CacheUserManager.getInstance().setLoginBean(loginBean);

       }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.log;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        mPresenter=new LoginPresenter(this);
    }

    @Override
    protected void initView() {

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = name.getText().toString();
                String s1 = password.getText().toString();
                if (!s.equals("")&&!s1.equals("")){
                    mPresenter.LoginData(s,s1);
                }else {
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisConnect() {

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }
}