package com.fiannce.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.view.ToolBar;

@Route(path = "/user/unLoginActivity")
public class unLoginActivity extends BaseActivity {


    private com.fiannce.framework.view.ToolBar toobarFake;
    private android.widget.ImageView mindImg;
    private android.widget.TextView tv;
    private android.widget.Button bt;

    @Override
    protected void initView() {

        toobarFake = (ToolBar) findViewById(R.id.toobar_fake);
        mindImg = (ImageView) findViewById(R.id.mind_img);
        tv = (TextView) findViewById(R.id.tv);
        bt = (Button) findViewById(R.id.bt);
        toobarFake.setToolbarListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login.txt", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("boo", false);
                edit.putString("name", "");
                edit.putString("pass", "");
                edit.commit();
                ARouter.getInstance().build("/main/MainActivity").withInt("", 0).navigation();
            }
        });
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_un_login;
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}
