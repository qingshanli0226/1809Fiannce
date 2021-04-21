package com.example.designed.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.designed.MainActivity;
import com.example.designed.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.VersionBean;



public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    WelcomePresenter welcomePresenter;
    private final int ONE_FILSH = 0;
    private final int ALLFILSH = 1;
    private final int DELAT = 1*1000;//1秒
    private boolean homeFinsih = false;
    private boolean versioneFinsih = false;
    private boolean advFinsih = false;
    private ImageView iv;
    private TextView dao;
    @Override
    public void onHomeData(HomeBean homeBean) {
        handler.sendEmptyMessage(ONE_FILSH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息:" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        handler.sendEmptyMessage(ONE_FILSH);
    }

    @Override
    protected void initData() {
        welcomePresenter.getHomeData();
        welcomePresenter.getVersionData();
    }

    @Override
    protected void initPresenter() {
      welcomePresenter = new WelcomePresenter(this);

    }

    @Override
    protected int getLaoutId() {
        return R.layout.welcome;
    }



    @Override
    protected void initView() {


        iv = (ImageView) findViewById(R.id.iv);
        dao = (TextView) findViewById(R.id.dao);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
            Toast.makeText(this, "请求出现错误" + error, Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}
