package com.fiannce.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.view.ToolBar;

public class ManageActivity extends BaseActivity {


    private com.fiannce.framework.view.ToolBar toobarFake;

    @Override
    protected void initView() {

        toobarFake = (ToolBar) findViewById(R.id.toobar_fake);
        toobarFake.setToolbarListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_manage;
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
