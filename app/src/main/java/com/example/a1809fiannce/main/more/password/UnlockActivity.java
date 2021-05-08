package com.example.a1809fiannce.main.more.password;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.R;
import com.example.framework.BaseActivity;
import com.example.net.model.UnlockBean;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

public class UnlockActivity extends BaseActivity<UnlockPresenter> implements IUnlockView {

    private GestureLockView mGestureLockView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unlock;
    }

    @Override
    protected void initData() {

        mGestureLockView.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                httpPresenter.getUnlockData(result);
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
    }

    @Override
    public void getUnlockData(UnlockBean unlockBean) {
        LogUtils.json(unlockBean);
        if (unlockBean.getCode().equals("200")) {
            Toast.makeText(this, "设置手势密码成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "设置手势密码失败请重试", Toast.LENGTH_SHORT).show();
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