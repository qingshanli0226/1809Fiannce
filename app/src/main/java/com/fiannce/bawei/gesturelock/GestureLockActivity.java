package com.fiannce.bawei.gesturelock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.widget.TextView;
import android.widget.Toast;

import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.net.mode.GestureBean;
import com.fiannce.zhaoyuzan.R;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;
import com.wangnan.library.painter.JDFinancePainter;

import io.reactivex.annotations.Nullable;

public class GestureLockActivity extends BaseActivity<GesturePresenter> implements IGestureView {

    GestureLockView mGestureLockView;
    private int judge = 0;
    TextView mCurrentPassword;
    private ToolBar toolBar;

    @Override
    protected void initPresenter() {
        httpPresenter = new GesturePresenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        judge = intent.getIntExtra("judge", 0);
        if (judge == 0) {
            Toast.makeText(this, "系统错误请返回重试", Toast.LENGTH_SHORT).show();
            finish();
        }

        mGestureLockView.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                switch (judge) {
                    case 1:
                        httpPresenter.getUnlockData(result);
                        break;
                    case 2:
                        httpPresenter.verifyUnlockData(result);
                        break;
                    case 3:
                        Log.i("zyz", "initData: +33333");
                        httpPresenter.clearUnlockData(result);
                        break;
                }

            }
        });

    }

    @Override
    protected void initView() {
        mCurrentPassword = (TextView) findViewById(R.id.tv_current_passord);
        mGestureLockView = (GestureLockView) findViewById(R.id.glv);
        toolBar = findViewById(R.id.toolbar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture_lock;
    }

    @Override
    public void onGestureData(GestureBean gestureBean) {
        if (gestureBean.getCode().equals("200")) {
            Toast.makeText(this, gestureBean.getResult(), Toast.LENGTH_SHORT).show();
            CacheManager.getInstance().gesture =true;
            finish();
        } else {
            Toast.makeText(this, "设置手势密码失败请重试", Toast.LENGTH_SHORT).show();
            mGestureLockView.showErrorStatus(600);
        }
    }

    @Override
    public void verifyUnlockData(GestureBean gestureBean) {

    }

    @Override
    public void clearUnlockData(GestureBean gestureBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
