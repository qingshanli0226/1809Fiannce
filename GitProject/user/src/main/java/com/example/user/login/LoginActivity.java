package com.example.user.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.net.bean.LoginBean;
import com.example.user.R;
import com.example.user.register.RegisterActivity;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView{


    private EditText loginPhone;
    private EditText loginPwd;
    private Button login;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        loginPhone = (EditText) findViewById(R.id.login_phone);
        loginPwd = (EditText) findViewById(R.id.login_pwd);
        login = (Button) findViewById(R.id.login);
    }

    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void initData() {

        login.setOnClickListener(v -> {
            String phone = loginPhone.getText().toString().trim();
            String pwd = loginPwd.getText().toString().trim();
            if(TextUtils.isEmpty(phone) && TextUtils.isEmpty(pwd)){
                Toast.makeText(LoginActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
            } else{
                mPresenter.getLogin(phone,pwd);
            }
        });

    }


    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        finish();
        Bundle bundle = new Bundle();
        bundle.putInt("page",0);
        FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onLogin(LoginBean loginBean) {
        loadPage.showSuccessLayout();
        if(loginBean.getCode().equals("200")){
            SpUtil.putString(this, CommonConstant.SP_TOKEN,loginBean.getResult().getToken());
            //跳到主页面返回
            CacheUserManager.getInstance().setLoginBean(loginBean);
            Bundle bundle = new Bundle();
            bundle.putInt("page",0);
            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();
        } else{
            Toast.makeText(this, "失败："+loginBean, Toast.LENGTH_SHORT).show();
        }
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
}