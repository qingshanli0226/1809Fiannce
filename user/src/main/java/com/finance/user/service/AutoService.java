package com.finance.user.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.finance.framework.manager.CacheUserManager;
import com.finance.framework.sp.SPUtil;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.RegisterBean;
import com.finance.user.register.mvp.IRegisterView;
import com.finance.user.register.mvp.RegisterPresenter;

public class AutoService extends Service {
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new RegisterPresenter(new IRegisterView() {
            @Override
            public void onRegisterData(RegisterBean registerBean) {

            }

            @Override
            public void onLogin(LoginBean loginBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                SPUtil.putString(AutoService.this, "token",loginBean.getResult().getToken());
                //跳到主页面返回
                CacheUserManager.getInstance().setLoginBean(loginBean);
            }

            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showError(String msg) {

            }
        }).getAutoLogin(SPUtil.getString(this, "token"));
        return super.onStartCommand(intent, flags, startId);
    }
}
