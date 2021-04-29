package com.example.user.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.user.register.IUserView;
import com.example.user.register.UserPresenter;

public class AutoService extends Service {
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
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
                SpUtil.putString(AutoService.this, CommonConstant.SP_TOKEN,loginBean.getResult().getToken());
                //跳到主页面返回
                CacheUserManager.getInstance().setLoginBean(loginBean);
//                FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
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
        }).getAutoLogin(SpUtil.getString(this, CommonConstant.SP_TOKEN));
        return super.onStartCommand(intent, flags, startId);
    }
}