package com.example.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo.Demo;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.user.R;

import org.greenrobot.eventbus.EventBus;

@Route(path = Demo.AROUTE_PATH_EXIT_LOGIN)
public class ExitActivity extends BaseActivity {

    private ToolBar toolbar;
    private android.widget.Button exitLogin;

    @Override
    protected void initData() {

        toolBar.setToolbarListener(new ToolBar.IToolbarListener() {
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
        });

        exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                EventBus.getDefault().postSticky("exit_login");
                EventBus.getDefault().postSticky("change_message");
            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        exitLogin = (Button) findViewById(R.id.exit_login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exit;
    }
}