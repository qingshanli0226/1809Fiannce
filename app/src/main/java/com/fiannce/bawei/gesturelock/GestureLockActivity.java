package com.fiannce.bawei.gesturelock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.widget.TextView;
import android.widget.Toast;

import com.fiannce.framework.BaseActivity;
import com.fiannce.net.mode.GestureBean;
import com.fiannce.zhaoyuzan.R;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;
import com.wangnan.library.painter.JDFinancePainter;

import io.reactivex.annotations.Nullable;

public class GestureLockActivity extends BaseActivity<GesturePresenter> implements OnGestureLockListener,IGestureView {

    GestureLockView mGestureLockView;

    TextView mCurrentPassword;

    @Override
    protected void initPresenter() {
        httpPresenter = new GesturePresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mCurrentPassword = (TextView) findViewById(R.id.tv_current_passord);
        mGestureLockView = (GestureLockView) findViewById(R.id.glv);
        mGestureLockView.setPainter(new JDFinancePainter());
        mGestureLockView.setGestureLockListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture_lock;
    }

    /**
     * 解锁开始监听方法
     */
    @Override
    public void onStarted() {

    }

    /**
     * 解锁密码改变监听方法
     */
    @Override
    public void onProgress(String progress) {
        mCurrentPassword.setText("当前密码: " + progress);
    }

    /**
     * 解锁完成监听方法
     */
    @Override
    public void onComplete(String result) {
        if (TextUtils.isEmpty(result)) {
            return;
        } else if ("012345678".equals(result)) {
            Toast.makeText(GestureLockActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
            mGestureLockView.clearView();
        } else {
            Toast.makeText(GestureLockActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            mGestureLockView.showErrorStatus(400);
        }
    }

    @Override
    public void onGestureData(GestureBean gestureBean) {

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
