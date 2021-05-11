package com.example.a1809fiannce.Gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.framwork.base.BaseActivity;
import com.example.framwork.view.TobView;
import com.example.network.model.GesturePwd;
import com.itsxtt.patternlock.PatternLockView;

import java.util.ArrayList;

public class GestureActivity extends BaseActivity<GesturePresenter> implements CallGesture{
    private TextView textSucceed;
    private PatternLockView patternLockView;
    private String aPwd="";
    private String name;
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
                aPwd="";
                for (Integer integer : list) {
                    aPwd+=integer.toString();
                }

                if (name.equals("set")){
                    mPresenter.GestureData(aPwd);
                }else if (name.equals("reset")){
                    mPresenter.ResetPwd(aPwd);
                }else if (name.equals("verity")){
                    mPresenter.VerityPwd(aPwd);
                }

                return true;
            }
        });
    }

    @Override
    protected void initView() {
        textSucceed =findViewById(R.id.text_succeed);
        patternLockView = findViewById(R.id.patternLockView);
        name=getIntent().getStringExtra("name");
    }

    @Override
    protected int FindLayout1() {
        return R.layout.activity_gesture;
    }

    @Override
    public void OnGestureData(GesturePwd gesturePwd) {
        if (gesturePwd.getCode().equals("200")){
            textSucceed.setText("再输入一遍");
            name="verity";
        }else {
            Toast.makeText(this, ""+gesturePwd.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnResetData(GesturePwd gesturePwd) {
        Log.i("zx", "OnResetData: "+gesturePwd.toString());
    }

    @Override
    public void OnVerityData(GesturePwd gesturePwd) {
        if (gesturePwd.getCode().equals("200")){

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
            textSucceed.setText("和上次输入不一致");
        }
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {

        Log.i("zx", "Error: "+error);

    }

}