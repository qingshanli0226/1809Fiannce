package com.example.user.log;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framwork.base.BaseActivity;
import com.example.framwork.call.FiannceARouter;
import com.example.framwork.call.FiannceUserManager;
import com.example.network.model.LogBean;
import com.example.user.R;

public class LogActivity extends BaseActivity<LogPresenter> implements LogCallBack{
    private String name;
    private String pwd;
    @Override
    protected void initData() {
        mPresenter=new LogPresenter(this);
        mPresenter.LogData(name,pwd);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
         name = intent.getStringExtra("name");
         pwd = intent.getStringExtra("pwd");

    }

    @Override
    protected int FindLayout1() {
        return R.layout.main_log;
    }

    @Override
    public void LogData(LogBean logBean) {
        pageView.ShowSuccess();
        if (logBean.getCode().equals("200")){
            Toast.makeText(this, ""+logBean.getMessage(), Toast.LENGTH_SHORT).show();
            FiannceUserManager.getInstance().setLog(true);
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            bundle.putInt("num",0);
            FiannceARouter.getFiannceARouter().getAppManager().OpenMainActivity(LogActivity.this,bundle);
        }else {
            Toast.makeText(this, ""+logBean.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void ShowLoading() {
        pageView.ShowLoad();
    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
