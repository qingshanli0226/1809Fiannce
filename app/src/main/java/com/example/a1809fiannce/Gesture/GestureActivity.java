package com.example.a1809fiannce.Gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.a1809fiannce.R;
import com.example.framwork.base.BaseActivity;
import com.example.network.model.GesturePwd;
import com.itsxtt.patternlock.PatternLockView;

import java.util.ArrayList;

public class GestureActivity extends BaseActivity<GesturePresenter> implements CallGesture{
    private PatternLockView patternLockView;
    private String aPwd="";

    @Override
    protected void initData() {
        aPwd="";
        mPresenter=new GesturePresenter(this);
        patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(ArrayList<Integer> list) {

            }

            @Override
            public boolean onComplete(ArrayList<Integer> list) {
                /*
                 * A return value required
                 * if the pattern is not correct and you'd like change the pattern to error state, return false
                 * otherwise return true
                 */

                for (Integer integer : list) {
                    aPwd+=integer.toString();
                }
                Log.i("zx", "onComplete: "+aPwd);
                mPresenter.GestureData(aPwd);
                return true;
            }
        });
    }

    @Override
    protected void initView() {
        patternLockView = findViewById(R.id.patternLockView);
    }

    @Override
    protected int FindLayout1() {
        return R.layout.activity_gesture;
    }

    @Override
    public void OnGestureData(GesturePwd gesturePwd) {
        Log.i("zx", "OnGestureData: "+gesturePwd.toString());
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {

    }
}