package com.example.user.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.utils.TextUtils;
import com.example.commom.FianceConstants;
import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.user.R;

public class LoginActivity extends BaseActivity<LoginPresneter> implements ILoginView {

    private ToolBar toolbar;
    private EditText actLogPassword;
    private TextView actLogBut;
    private EditText actLogUser;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String buser = bundle.getString("user");
            String bpwd = bundle.getString("pwd");
            if (!TextUtils.isEmpty(buser) && !TextUtils.isEmpty(bpwd)) {
                actLogUser.setText(buser);
                actLogPassword.setText(bpwd);
            }
        }


        actLogBut.setOnClickListener(view -> {
            String user = actLogUser.getText().toString().trim();
            String pwd = actLogPassword.getText().toString().trim();
            if (user.equals("") || pwd.equals("")) {
                Toast.makeText(this, R.string.theUsernameAndPasswordCannotBeEmpty, Toast.LENGTH_SHORT).show();
                return;
            }
            httpPresenter.getRegisterData(user,pwd);

        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresneter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        actLogPassword = (EditText) findViewById(R.id.act_log_password);
        actLogBut = (TextView) findViewById(R.id.act_log_but);
        actLogUser = (EditText) findViewById(R.id.act_log_user);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void getLoginData(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")) {
            FiannceUserManager.getInstance().setLoginBean(loginBean);
            SpUtil.setString(this,FianceConstants.TOKEN_KEY,loginBean.getResult().getToken());
            FiannceArouter.getInstance().build(FianceConstants.MAIN_PATH).navigation();
        }
    }
}