package com.fiannce.user.exitLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.user.R;
import com.fiannce.user.UserManager;
import com.fiannce.user.login.LoginActivity;

public class ExitLoginActivity extends BaseActivity {


    private android.widget.ImageView ivUserIcon;
    private android.widget.TextView tvUserChange;
    private android.widget.Button btnUserLogout;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        btnUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserManager.getInstance().hasUserInfo(ExitLoginActivity.this)){
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                }else {

                }

            }
        });
    }

    @Override
    protected void initView() {
        ivUserIcon = findViewById(R.id.iv_user_icon);
        tvUserChange = findViewById(R.id.tv_user_change);
        btnUserLogout = findViewById(R.id.btn_user_logout);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exit_login;
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
