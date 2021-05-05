package com.example.myfinancial.mymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseActivity;
import com.example.framework.mannager.FiannceUserMannager;
import com.example.myfinancial.R;

public class UserMessageActivity extends BaseActivity {

    private android.widget.ImageView userMessageImage;
    private android.widget.TextView changeImage;
    private android.widget.Button logOut;

    @Override
    protected int getbandLayout() {
        return R.layout.activity_user_message;
    }

    @Override
    protected void initView() {
        userMessageImage = (ImageView) findViewById(R.id.userMessageImage);
        changeImage = (TextView) findViewById(R.id.changeImage);
        logOut = (Button) findViewById(R.id.logOut);
    }

    @Override
    protected void initData() {
        //退出登录
        logOut.setOnClickListener(v -> {
            FiannceUserMannager.getInstance().setIsLwogin(false);
            finish();
        });
    }

    @Override
    protected void initPresenter() {

    }
}