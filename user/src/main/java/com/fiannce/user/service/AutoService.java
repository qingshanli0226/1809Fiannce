package com.fiannce.user.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.commond.CommonConstant;
import com.fiannce.commond.SpUtil;
import com.fiannce.framework.manager.CacheUserManager;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.user.register.IUserView;
import com.fiannce.user.register.UserPresenter;

public class AutoService extends Service {
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("AutoService", "112211");
        new UserPresenter(new IUserView() {
            @Override
            public void onRegister(RegisterBean registerBean) {

            }

            @Override
            public void onLogin(LoginBean loginBean) {

            }

            @Override
            public void onAutoLogin(LoginBean loginBean) {
                SpUtil.putString(AutoService.this, CommonConstant.SP_TOKEN, loginBean.getResult().getToken());
                Log.d("zyz", "123123");
                CacheUserManager.getInstance().setLoginBean(loginBean);
//                FrameArouter.getInstance().build(CommonConstant.APP_MAIN_PATH).navigation();
                ARouter.getInstance().build("/main/MainActivity").navigation();
            }

            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showToast(String error) {

            }
        }).getAutoLogin(SpUtil.getString(this, CommonConstant.SP_TOKEN));

    }
}
