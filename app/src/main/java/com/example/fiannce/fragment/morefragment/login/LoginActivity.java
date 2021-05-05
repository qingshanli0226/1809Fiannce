package com.example.fiannce.fragment.morefragment.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fiannce.R;
import com.example.fiannce.fragment.BeanBack;
import com.example.framework.BaseActivity;
import com.example.framework.FiannceARouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.mode.AllBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.LogBean;
import com.example.net.mode.RegBean;
import com.example.net.mode.UpdateBean;

public class LoginActivity extends BaseActivity<LogPresenter> implements BeanBack {

    private String pwd;
    private String name;

    @Override
    public void HomeData(HomeBean homeBean) {

    }

    @Override
    public void UpdateData(UpdateBean updateBean) {

    }

    @Override
    public void AllData(AllBean allBean) {

    }

    @Override
    public void RegData(RegBean regBean) {

    }

    @Override
    public void LogData(LogBean logBean) {
        pageView.ShowSuccess();
        if (logBean.getCode().equals("200")){
            FiannceUserManager.getInstance().setLogin(true);

            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            bundle.putInt("num",0);

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
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}