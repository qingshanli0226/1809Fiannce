package com.example.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.utils.PathConstant;

@Route(path = PathConstant.USER_REGISTER_PATH)
public class RegisterActivity extends BaseActivity implements IRegisterView{

    private EditText registerPhone;
    private EditText registerName;
    private EditText registerPwd;
    private TextView registerYesPwd;
    private Button register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

        registerPhone = (EditText) findViewById(R.id.register_phone);
        registerName = (EditText) findViewById(R.id.register_name);
        registerPwd = (EditText) findViewById(R.id.register_pwd);
        registerYesPwd = (TextView) findViewById(R.id.register_yes_pwd);
        register = (Button) findViewById(R.id.register);
    }

    @Override
    public void initPresenter() {
        mPresenter = new RegisterPresenter(this);

    }

    @Override
    public void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断不为空

                //判断两次密码不一样


            }
        });

    }


    @Override
    public void onRegister(RegisterBean registerBean) {

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
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickRight() {

    }
}