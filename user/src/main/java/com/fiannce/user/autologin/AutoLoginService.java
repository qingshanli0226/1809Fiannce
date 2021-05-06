package com.fiannce.user.autologin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.service.autofill.AutofillService;

import com.fiannce.framework.SpUtiles;
import com.fiannce.net.mode.AutoLoginBean;

public class AutoLoginService extends Service {
    public AutoLoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String string = SpUtiles.getString(AutoLoginService.this);
        if (string.equals("")){

        }else {
            new AutoLoginPresenter(new IAutoLoginView() {
                @Override
                public void getAutoLogin(AutoLoginBean autoLoginBean) {
                    AutoLoginBean.ResultBean result = autoLoginBean.getResult();
                    String token = result.getToken();
                    if (autoLoginBean.getCode().equals("200")){
                        SpUtiles.putString(AutoLoginService.this,token);

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
            });
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
