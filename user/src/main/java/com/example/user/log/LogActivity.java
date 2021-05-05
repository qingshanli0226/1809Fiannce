package com.example.user.log;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.common.UserCallBack;
import com.example.framwork.base.BaseActivity;
import com.example.framwork.call.FiannceARouter;
import com.example.framwork.call.FiannceUserManager;
import com.example.network.model.LogBean;
import com.example.user.R;
import com.example.common.Squilts;

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
            FiannceUserManager.getInstance().setIsLog(logBean);
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            UserCallBack.getInstance().setName(name);
            bundle.putInt("num",0);
            Squilts.putString(LogActivity.this,logBean.getResult().getToken());
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
