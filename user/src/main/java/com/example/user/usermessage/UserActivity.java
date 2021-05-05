package com.example.user.usermessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.commom.FianceConstants;
import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.user.R;

public class UserActivity extends BaseActivity{

    private TextView actUserBacklogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initData() {
        actUserBacklogin.setOnClickListener(view -> {
            FiannceUserManager.getInstance().setLoginBean(null);
            SpUtil.setString(this, FianceConstants.TOKEN_KEY,"");
            FiannceArouter.getInstance().build(FianceConstants.MAIN_PATH).navigation();
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftImgClick() {
        super.onLeftImgClick();
        finish();
    }

    @Override
    protected void initView() {

        actUserBacklogin = (TextView) findViewById(R.id.act_user_backlogin);
    }
}