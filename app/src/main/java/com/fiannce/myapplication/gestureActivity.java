package com.fiannce.myapplication;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

@Route(path = "/gesture/GestureActivity")
public class gestureActivity extends AppCompatActivity {

    private GestureLockView gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        initView();
        initData();
    }

    private void initData() {
        gesture.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                if (TextUtils.isEmpty(result)){
                    return;
                }if ("012345678".equals(result)){

                }else {
                    gesture.showErrorStatus(600);
                }
            }
        });
    }

    private void initView() {
        gesture = (GestureLockView) findViewById(R.id.gesture);
    }
}
