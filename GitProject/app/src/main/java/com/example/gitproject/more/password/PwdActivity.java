package com.example.gitproject.more.password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.common.CommonConstant;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.framework.view.ToolBar;
import com.example.gitproject.R;
import com.example.gitproject.more.password.status.GestureStatus;
import com.example.net.RetrofitManager;
import com.example.net.bean.GesturePassword;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

import java.util.HashMap;

import javax.xml.transform.sax.TemplatesHandler;

public class PwdActivity extends BaseActivity<GesturePresenter> implements IGestureView {


    private ToolBar toolbar;
    private GestureLockView gle;
    private boolean isPwd = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pwd;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        gle = (GestureLockView) findViewById(R.id.gle);
    }

    @Override
    public void initPresenter() {
        mPresenter = new GesturePresenter(this);
    }

    @Override
    public void initData() {
        gle.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put("gPassword", result);
                if(GestureStatus.getInstance().getPwdStatus() == CommonConstant.STATUS_SET){
                    //设置手势密码
                    if(isPwd){
                        mPresenter.setGeseture(stringStringHashMap);
                        Toast.makeText(PwdActivity.this, "再次输入密码", Toast.LENGTH_SHORT).show();
                    } else{
                        String getgPassword = (String) CacheUserManager.getInstance().getLoginBean().getResult().getgPassword();
                        if (TextUtils.isEmpty(result)) {
                            return;
                        } else if (getgPassword.equals(result)) {
                            Toast.makeText(PwdActivity.this, "完成", Toast.LENGTH_SHORT).show();
                            gle.clearView();
                            finish();
                            Bundle bundle = new Bundle();
                            bundle.putInt("page",0);
                            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();
                        } else {
                            Toast.makeText(PwdActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if(GestureStatus.getInstance().getPwdStatus() == CommonConstant.STATUS_LOGIN){
                    //登录手势密码
                    mPresenter.loginGeseture(stringStringHashMap);

                } else if(GestureStatus.getInstance().getPwdStatus() == CommonConstant.STATUS_CLEAR){
                    //清除手势密码
                    String getgPassword = (String) CacheUserManager.getInstance().getLoginBean().getResult().getgPassword();
                    if (TextUtils.isEmpty(result)) {
                        return;
                    } else if (getgPassword.equals(result)) {
                        mPresenter.clearGeseture(stringStringHashMap);
                    } else {
                        Toast.makeText(PwdActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                    }

                } else{
                    Toast.makeText(PwdActivity.this, "错误", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onSetGesture(GesturePassword gesturePassword) {
        if (gesturePassword.getCode().equals("200")) {
            isPwd = false;
            gle.clearView();

        }

    }

    @Override
    public void onLoginGesture(GesturePassword gesturePassword) {
        if(gesturePassword.getCode().equals("200")){
            gle.clearView();
            Bundle bundle = new Bundle();
            bundle.putInt("page",2);
            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();
        }
    }

    @Override
    public void onClearGesture(GesturePassword gesturePassword) {
        if(gesturePassword.getCode().equals("200")){
            gle.clearView();

            finish();
            Bundle bundle = new Bundle();
            bundle.putInt("page",0);
            FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).with(bundle).navigation();
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

    @Override
    public void destroy() {
        super.destroy();

    }
}