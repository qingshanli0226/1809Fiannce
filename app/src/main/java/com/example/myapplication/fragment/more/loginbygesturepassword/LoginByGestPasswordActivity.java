package com.example.myapplication.fragment.more.loginbygesturepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.model.GesturePasswordBean;
import com.example.myapplication.R;
import com.example.sp.SpUtils;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.Demo.AROUTE_PATH_LOGINBYGESTUREPASSWORD;

@Route(path = AROUTE_PATH_LOGINBYGESTUREPASSWORD)
public class LoginByGestPasswordActivity extends BaseActivity<LoginByGesturePasswordPresenter> implements ILoginByGesturepasswordView {

    private Map<String,String> map = new HashMap<>();
    private com.example.framework.view.ToolBar toolbar;
    private com.wangnan.library.GestureLockView glvs;

    @Override
    protected void initData() {

        glvs.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                if (!result.equals("")){
                    String pwdResult = SpUtils.getGesturePwdResult(LoginByGestPasswordActivity.this);
                    Log.i("zrf", "onComplete: "+pwdResult);
                    if (pwdResult.equals(result)){
                        map.put("gPassword",result);
                        httpPresenter.postLoginByGesturePassword(map);
                    }else {
                        glvs.clearView();
                        Toast.makeText(LoginByGestPasswordActivity.this, "密码错误请重新尝试", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginByGesturePasswordPresenter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        glvs = (GestureLockView) findViewById(R.id.glvs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_by_gest_password;
    }

    @Override
    public void getLoginByGesturePwd(GesturePasswordBean gesturePasswordBean) {
        if (gesturePasswordBean.getCode().equals("200")){
            Toast.makeText(this, "成功啦", Toast.LENGTH_SHORT).show();
            finish();
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