package com.example.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.Squilts;
import com.example.common.UserCallBack;
import com.example.framework.BaseActivity;
import com.example.framework.FiannceARouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.ToolBarView;
import com.example.net.mode.LogBean;
import com.example.user.R;

public class LoginActivity extends BaseActivity<LogPresenter> implements LogCallBack {

    private String pwd;
    private String name;
    private ToolBarView tob;
    private EditText logPhone;
    private EditText logUse;
    private EditText logPwd;
    private EditText logPhoneTwo;
    private Button log;


    @Override
    protected void initData() {
        httpPresenter = new LogPresenter(this);
        httpPresenter.LogData(name, pwd);
            Log.i("123", "initView: 3333333333333");
    }

    @Override
    protected void initView() {

        tob = (ToolBarView) findViewById(R.id.tob);
        logPhone = (EditText) findViewById(R.id.log_phone);
        logUse = (EditText) findViewById(R.id.log_use);
        logPwd = (EditText) findViewById(R.id.log_pwd);
        logPhoneTwo = (EditText) findViewById(R.id.log_phone_two);
        log = (Button) findViewById(R.id.log);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        pwd = intent.getStringExtra("pwd");

        Log.i("123", "initView: "+name+pwd);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void LogData(LogBean logBean) {
        //pageView.ShowSuccess();
        if (logBean.getCode().equals("200")){
            FiannceUserManager.getInstance().setIsLog(logBean);
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            UserCallBack.getInstance().setName(name);
            bundle.putInt("num",0);

            Squilts.putString(LoginActivity.this,logBean.getResult().getToken());

            FiannceARouter.getFiannceARouter().getAppManager().OpenMainActivity(LoginActivity.this,bundle);
        }else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void showLoading() {
        pageView.ShowLoad();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
        Log.i("123", "showError: "+error);
    }

}