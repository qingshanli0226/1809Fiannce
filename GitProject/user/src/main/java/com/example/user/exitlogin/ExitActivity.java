package com.example.user.exitlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.net.bean.GesturePassword;
import com.example.user.R;
import com.google.android.material.tabs.TabLayout;

public class ExitActivity extends BaseActivity<ExitPresenter> implements IExitView {


    private ImageView exitHead;
    private TextView exitName;
    private Button exit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_exit;
    }

    @Override
    public void initView() {

        exitHead = (ImageView) findViewById(R.id.exit_head);
        exitName = (TextView) findViewById(R.id.exit_name);
        exit = (Button) findViewById(R.id.exit);
    }

    @Override
    public void initPresenter() {
        mPresenter = new ExitPresenter(this);
    }

    @Override
    public void initData() {
        int anInt = FrameArouter.getInstance().getIntent().getBundleExtra("param").getInt("img");
        exitHead.setImageResource(anInt);
        exit.setOnClickListener(v -> {


            mPresenter.getExit();
        });


    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onExit(GesturePassword gesturePassword) {
        if(gesturePassword.getCode().equals("200")){
            CacheUserManager.getInstance().setLoginBean(null);
            SpUtil.putString(this, CommonConstant.SP_TOKEN,"");
            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
        }
    }
}