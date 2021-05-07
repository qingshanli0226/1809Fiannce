package com.example.user.self;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.common.Squilts;
import com.example.common.UserCallBack;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.mode.LogBean;

public class SelfService extends Service {

    public SelfService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new SelfPresenter(new SelfCallBack() {
            @Override
            public void LogData(LogBean logBean) {
                if (logBean != null){
                    if (logBean.getCode().equals("200")){
                        UserCallBack.getInstance().setName(logBean.getResult().getName());

                        FiannceUserManager.getInstance().setLogin(logBean);

                        Squilts.putString(SelfService.this,logBean.getResult().getToken());
                    }
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
        }).RequestSelf(Squilts.getString(this));

        return super.onStartCommand(intent, flags, startId);
    }
}
