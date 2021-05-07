package com.example.user.register;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.login.LoginActivity;
import com.example.framework.BaseActivity;
import com.example.net.ToolBarView;
import com.example.net.mode.AllBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.LogBean;
import com.example.net.mode.RegBean;
import com.example.net.mode.UpdateBean;

public class RegisterActivity extends BaseActivity<RegPresenter> implements RegisterCallBack {

    private ToolBarView tob;
    private EditText phone;
    private EditText use;
    private EditText pwd;
    private Button register;
    private String phoneNum;
    private String name;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        httpPresenter = new RegPresenter(this);

        phoneNum = phone.getText().toString();
        name = use.getText().toString();
        password = pwd.getText().toString();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = phone.getText().toString();
                name =use.getText().toString();
                password = pwd.getText().toString();

                if (phoneNum.equals("")&&name.equals("")&&password.equals("")){
                    Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                httpPresenter.RegData(name,password);
            }
        });
    }

    @Override
    protected void initView() {
        tob = (ToolBarView) findViewById(R.id.tob);
        phone = (EditText) findViewById(R.id.phone);
        use = (EditText) findViewById(R.id.use);
        pwd = (EditText) findViewById(R.id.pwd);
        register = (Button) findViewById(R.id.register);
    }


    @Override
    public void RegData(RegBean regBean) {
        if (regBean.getCode().equals("200")){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

            intent.putExtra("name",name);
            intent.putExtra("pwd",password);

            startActivity(intent);

        }else {
            Toast.makeText(this, ""+regBean.getMessage(), Toast.LENGTH_SHORT).show();
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