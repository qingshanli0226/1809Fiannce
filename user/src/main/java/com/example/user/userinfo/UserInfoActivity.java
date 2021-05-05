package com.example.user.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.user.R;

public class UserInfoActivity extends BaseActivity {

    private com.example.framework.view.ToolBar toolbar;
    private android.widget.ImageView userImg;
    private android.widget.Button out;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        userImg = (ImageView) findViewById(R.id.user_img);
        out = (Button) findViewById(R.id.out);
    }

    @Override
    protected void initData() {
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiannceUserManager.getInstance().setLoginBean(null);

                SpUtil.setString(UserInfoActivity.this,"");

                FiannceArouter.getInstance().getIAppInterface().openMainActivity(UserInfoActivity.this,null);
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}