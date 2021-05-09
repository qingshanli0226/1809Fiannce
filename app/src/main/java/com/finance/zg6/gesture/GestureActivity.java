package com.finance.zg6.gesture;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.finance.zg.R;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

public class GestureActivity extends AppCompatActivity {

    private GestureLockView mGestureLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);


        initView();
        initData();
    }

    private void initData() {
        
        mGestureLockView.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String s) {

            }

            @Override
            public void onComplete(String s) {
                if (TextUtils.isEmpty(s)){
                    return;
                }if ("12".equals(s)){
                    Toast.makeText(GestureActivity.this, "解锁成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(GestureActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    mGestureLockView.showErrorStatus(600);
                }
            }
        });
    }

    private void initView() {
        mGestureLockView = (GestureLockView) findViewById(R.id.mGestureLockView);
    }
}