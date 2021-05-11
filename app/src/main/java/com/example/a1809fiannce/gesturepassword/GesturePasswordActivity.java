package com.example.a1809fiannce.gesturepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.R;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.mode.RegisterBean;
import com.wangnan.library.GestureLockThumbnailView;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

public class GesturePasswordActivity extends BaseActivity<GesturePasswordPresenter> implements IGesturePassword {

    private com.wangnan.library.GestureLockView gestureLock;
    private com.example.framework.view.ToolBar toolbar;
    private com.wangnan.library.GestureLockThumbnailView gestView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture_password;
    }

    @Override
    protected void initView() {
        gestureLock = (GestureLockView) findViewById(R.id.gestureLock);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        gestView = (GestureLockThumbnailView) findViewById(R.id.gestView);
    }

    @Override
    protected void initData() {
        gestureLock.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                gestureLock.clearView();

                gestView.setThumbnailView(result, Color.parseColor("#ff9900"));
                if (httpPresenter!=null){
                    httpPresenter.getGesturePassword(result);
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter=new GesturePasswordPresenter(this);
    }

    @Override
    public void onGesturePassword(RegisterBean registerBean) {
        LogUtils.e(registerBean.getCode());
        if (registerBean.getCode().equals("200")){
            finish();
        }
    }
}