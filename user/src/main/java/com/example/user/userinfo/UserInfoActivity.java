package com.example.user.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.mode.RegisterBean;
import com.example.user.R;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements IUserInfo {

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
                if (httpPresenter!=null){
                    httpPresenter.getUserInfo();
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new UserInfoPresenter(this);
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @Override
    public void onUserInfo(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")){
            FiannceUserManager.getInstance().setLoginBean(null);

            SpUtil.setString(UserInfoActivity.this,"");

            Toast.makeText(this, getResources().getString(R.string.logOut), Toast.LENGTH_SHORT).show();

            FiannceArouter.getInstance().getIAppInterface().openMainActivity(UserInfoActivity.this,null);
        }
    }
}