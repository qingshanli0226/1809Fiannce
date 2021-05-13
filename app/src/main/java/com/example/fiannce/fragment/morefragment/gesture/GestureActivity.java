package com.example.fiannce.fragment.morefragment.gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiannce.R;
import com.example.framework.BaseActivity;
import com.example.net.mode.GestureBean;
import com.itsxtt.patternlock.PatternLockView;

import java.util.ArrayList;

public class GestureActivity extends BaseActivity<GesturePresenter> implements CallGesture {

    private TextView textSuucceed;
    private PatternLockView patternLockView;
    private String aPwd = "";
    private String name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture;
    }

    @Override
    protected void initPresenter() {

    }

    //
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
                aPwd = "";

                for (Integer integer : arrayList){
                    aPwd += integer.toString();
                }

                if (name.equals("set")){
                    httpPresenter.GestureData(aPwd);
                }else if (name.equals("reset")){
                    httpPresenter.ResePwd(aPwd);
                }else if (name.equals("verity")){
                    httpPresenter.VerityPwd(aPwd);
                }

                return true;
            }
        });
    }

    @Override
    protected void initView() {
        textSuucceed = findViewById(R.id.text_succeed);
        patternLockView = (PatternLockView) findViewById(R.id.patternLockView);
        name = getIntent().getStringExtra("name");
    }

    @Override
    public void onGestureData(GestureBean gestureBean) {
        if (gestureBean.getCode().equals("200")){
            textSuucceed.setText("再次输入一遍");
            name = "verity";
        }else {
            Toast.makeText(this, "额", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResetData(GestureBean gestureBean) {

    }

    @Override
    public void onVerityData(GestureBean gestureBean) {
        if (gestureBean.getCode().equals("200")){
            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.setAction("verity");
            sendBroadcast(intent);

            SharedPreferences sharedPreferences = getSharedPreferences("setSucceed", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isSet",true);
            edit.commit();

            finish();
        }else {
            Toast.makeText(this, "和上次输入不一样", Toast.LENGTH_SHORT).show();
        }
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