package com.example.user;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.frame.CacheUserManager;
import com.example.frame.SpUtil;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

public class AutoService extends Service {
    public AutoService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new UserPresenter(new IUserView() {
            @Override
            public void onRegister(RegisterBean registerBean) {

            }

            @Override
            public void onLogin(LoginBean loginBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                SpUtil.putString(AutoService.this,"token",loginBean.getResult().getToken());
                CacheUserManager.getInstance().setLoginBean(loginBean);

            }

            @Override
            public void showLoaing() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showError(String msg) {

            }
        }).getAutoLogin(SpUtil.getString(this,"token"));
        return super.onStartCommand(intent, flags, startId);
    }
}
