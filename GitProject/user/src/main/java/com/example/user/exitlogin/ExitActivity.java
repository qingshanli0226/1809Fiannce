package com.example.user.exitlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.user.R;

public class ExitActivity extends BaseActivity {


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

    }

    @Override
    public void initData() {
        int anInt = FrameArouter.getInstance().getIntent().getBundleExtra("param").getInt("img");
        exitHead.setImageResource(anInt);

        exit.setOnClickListener(v -> {
            CacheUserManager.getInstance().setLoginBean(null);
            SpUtil.putString(this, CommonConstant.SP_TOKEN,"");
            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
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
}