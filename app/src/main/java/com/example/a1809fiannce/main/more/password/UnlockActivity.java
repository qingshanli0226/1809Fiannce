package com.example.a1809fiannce.main.more.password;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.R;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.UnlockBean;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

public class UnlockActivity extends BaseActivity<UnlockPresenter> implements IUnlockView {

    private GestureLockView mGestureLockView;
    private int judge = 0;
    private ToolBar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unlock;
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        judge = intent.getIntExtra("judge", 0);
        if (judge == 0) {
            Toast.makeText(this, "系统错误请返回重试", Toast.LENGTH_SHORT).show();
            finish();
        }
        Log.i("wppp", "initData: "+judge);

        switch (judge) {
            case 1:
                toolbar.setCenterTitle(getResources().getString(R.string.setGesturePassword));
                break;
            case 2:
                toolbar.setCenterTitle(getResources().getString(R.string.resetTheGesturePassword));
                break;
            case 3:
                break;
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
                        Log.i("wppp", "initData: +33333");
                        httpPresenter.clearUnlockData(result);
                        break;
                }

            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UnlockPresenter(this);
    }

    @Override
    protected void initView() {
        mGestureLockView = (GestureLockView) findViewById(R.id.act_inlock_glv);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
    }

    @Override
    public void onUnlockData(UnlockBean unlockBean) {
        LogUtils.json(unlockBean);
        if (unlockBean.getCode().equals("200")) {
            Toast.makeText(this, unlockBean.getResult(), Toast.LENGTH_SHORT).show();
            CacheManager.getInstance().gesture =true;
            finish();
        } else {
            Toast.makeText(this, "设置手势密码失败请重试", Toast.LENGTH_SHORT).show();
            mGestureLockView.showErrorStatus(600);
        }
    }

    @Override
    public void verifyUnlockData(UnlockBean unlockBean) {

    }

    @Override
    public void clearUnlockData(UnlockBean unlockBean) {
        if (unlockBean.getCode().equals("200")) {
            Toast.makeText(this, "重置手势密码成功", Toast.LENGTH_SHORT).show();
            CacheManager.getInstance().gesture =false;
            finish();
        } else {
            Toast.makeText(this, "重置手势密码失败请重试", Toast.LENGTH_SHORT).show();
            mGestureLockView.showErrorStatus(600);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
        LogUtils.e(error);
    }
}