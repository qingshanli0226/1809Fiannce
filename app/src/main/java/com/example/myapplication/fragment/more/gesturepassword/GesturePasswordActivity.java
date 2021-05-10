package com.example.myapplication.fragment.more.gesturepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

import static com.example.demo.Demo.AROUTE_PATH_GESTUREPASSWORD;

@Route(path = AROUTE_PATH_GESTUREPASSWORD)
public class GesturePasswordActivity extends BaseActivity<GesturePasswordPresenter> implements IGesturePasswordView{


    private ToolBar toolbar;
    private GestureLockView glv;
    private Map<String,String> map = new HashMap<>();
    private String oneResult = "";
    private boolean is_one = false;
    private android.widget.TextView tishi;

    @Override
    protected void initData() {

        glv.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                if (!result.equals("") && is_one == false){
                    map.put("gPassword",result);
                    httpPresenter.postGesturePassword(map);
                    oneResult = result;
                    is_one = true;
                    tishi.setText("请再次输入手势密码");
                    tishi.setVisibility(View.VISIBLE);
                    glv.clearView();
                }else if (is_one == true && result.equals(oneResult) ){
                    tishi.setText("设置成功");
                    SpUtils.putGesturePwdResule(GesturePasswordActivity.this,result);

                    glv.clearView();
                    finish();
                }else if (is_one == true && !result.equals(oneResult)){
                    tishi.setText("密码错误请重新输入");
                    is_one=false;
                    glv.clearView();
                }
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new GesturePasswordPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        glv = (GestureLockView) findViewById(R.id.glv);
        tishi = (TextView) findViewById(R.id.tishi);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture_password;
    }

    @Override
    public void getGesturePassword(GesturePasswordBean gesturePasswordBean) {
        Log.i("zrf", "getGesturePassword: "+gesturePasswordBean.getCode());
        if (gesturePasswordBean.getCode().equals("200")){
//            Toast.makeText(this, "设置完成", Toast.LENGTH_SHORT).show();
            SpUtils.putGestureBoolean(GesturePasswordActivity.this,true);
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