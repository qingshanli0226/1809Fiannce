package com.example.user.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.CommonConstant;
import com.example.framework.BaseActivity;
import com.example.framework.module.FrameArouter;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.R;


@Route(path = CommonConstant.USER_REGISTER_PATH)
public class RegisterActivity extends BaseActivity<UserPresenter> implements IUserView {

    private EditText registerPhone;
    private EditText registerName;
    private EditText registerPwd;
    private EditText registerYesPwd;
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
        registerYesPwd = (EditText) findViewById(R.id.register_yes_pwd);
        register = (Button) findViewById(R.id.register);
    }

    @Override
    public void initPresenter() {
        mPresenter = new UserPresenter(this);

    }
    private String phone;
    private String pwd;
    @Override
    public void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断不为空
                phone = registerPhone.getText().toString().trim();
                pwd = registerPwd.getText().toString().trim();
                String yesPwd = registerYesPwd.getText().toString().trim();

                if(TextUtils.isEmpty(phone)|| TextUtils.isEmpty(pwd)||TextUtils.isEmpty(yesPwd)){
                    Toast.makeText(RegisterActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                } else{
                    //判断两次密码不一样

                    if(pwd.equals(yesPwd)){
                        //一致
                        mPresenter.getRegister(phone,pwd);
                    } else{
                        //不一致
                        Toast.makeText(RegisterActivity.this, "两次密码不一样", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }


    @Override
    public void onRegister(RegisterBean registerBean) {

        loadPage.showSuccessLayout();
        if(registerBean.getCode().equals("200")){
            //跳转登录页面
            FrameArouter.getInstance().build(CommonConstant.USER_LOGIN_PATH).navigation();
        } else{
            Toast.makeText(this, ""+registerBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        Toast.makeText(this, ""+loginBean, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

    }

    @Override
    public void showLoading() {
        loadPage.showTransparentLoadLayout();
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