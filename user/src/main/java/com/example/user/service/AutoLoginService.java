package com.example.user.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.example.common.FiannceConstants;
import com.example.common.SpUtil;
import com.example.framework.mannager.FiannceUserMannager;
import com.example.net.bean.AutoBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.mvp.MorePresenter;
import com.example.user.mvp.MoreView;

import java.util.concurrent.Executor;

public class AutoLoginService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        new MorePresenter(new MoreView() {
            @Override
            public void initRegister(RegisterBean registerBean) {

            }

            @Override
            public void initLogin(LoginBean loginBean) {

            }

            @Override
            public void initAuto(AutoBean autoBean) {
                if (autoBean.getCode().equals("200")) {
                    SpUtil.setString(AutoLoginService.this, autoBean.getResult().getToken());
                    Log.d("AutoLoginService", "自动登陆成功");
                    FiannceUserMannager.getInstance().setIsLwogin(true);
                } else {
                    Log.d("AutoLoginService", "自动登陆失败");
                    FiannceUserMannager.getInstance().setIsLwogin(false);
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
        }).getAuto(SpUtil.getString(this, FiannceConstants.TOKEN_KEY));
    }

    public AutoLoginService() {
    }


    //代理人的类
   public class MyBinder extends Binder {
        public void myMethod() {
            startXz("");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    //下载
    private void startXz(String path) {
        Toast.makeText(this, "下载", Toast.LENGTH_SHORT).show();
    }
}