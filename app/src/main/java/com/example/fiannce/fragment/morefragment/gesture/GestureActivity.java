package com.example.fiannce.fragment.morefragment.gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.os.Bundle;

import com.example.fiannce.R;
import com.example.framework.BaseActivity;
import com.example.net.mode.GestureBean;
import com.itsxtt.patternlock.PatternLockView;

import java.util.ArrayList;

public class GestureActivity extends BaseActivity<GesturePresenter> implements CallGesture {

    private PatternLockView patternLockView;
    private String aPwd = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        aPwd = "";
        httpPresenter = new GesturePresenter(this);
        patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(ArrayList<Integer> arrayList) {

            }

            @Override
            public boolean onComplete(ArrayList<Integer> arrayList) {
                for (Integer integer : arrayList){
                    aPwd += integer.toString();
                }

                httpPresenter.GestureData(aPwd);

                return true;
            }
        });
    }

    @Override
    protected void initView() {
        patternLockView = (PatternLockView) findViewById(R.id.patternLockView);
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
    public void showError(String error) {

    }
}