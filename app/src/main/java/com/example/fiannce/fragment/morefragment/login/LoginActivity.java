package com.example.fiannce.fragment.morefragment.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.common.Squilts;
import com.example.fiannce.MainActivity;
import com.example.fiannce.R;
import com.example.fiannce.fragment.BeanBack;
import com.example.fiannce.fragment.mymoneyfragment.UserCallBack;
import com.example.framework.BaseActivity;
import com.example.framework.FiannceARouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.mode.AllBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.LogBean;
import com.example.net.mode.RegBean;
import com.example.net.mode.UpdateBean;

public class LoginActivity extends BaseActivity<LogPresenter> implements LogCallBack {

    private String pwd;
    private String name;

    @Override
    public void LogData(LogBean logBean) {
        pageView.ShowSuccess();
        if (logBean.getCode().equals("200")){
            Toast.makeText(this, "log", Toast.LENGTH_SHORT).show();
            FiannceUserManager.getInstance().setLogin(logBean);

            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            UserCallBack.getInstance().setName(name);
            bundle.putInt("num",0);

            Squilts.putString(LoginActivity.this,logBean.getResult().getToken());

            FiannceARouter.getFiannceARouter().getAppManager().OpenMainActivity(LoginActivity.this,bundle);

        }else {
            Toast.makeText(this, ""+logBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        httpPresenter = new LogPresenter(this);
        httpPresenter.LogData(name, pwd);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        pwd = intent.getStringExtra("pwd");
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
        //Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }

}