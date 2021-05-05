package com.example.user.auto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.common.UserCallBack;
import com.example.framwork.call.FiannceUserManager;
import com.example.network.model.LogBean;
import com.example.common.Squilts;

public class AutoService extends Service {
    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("zx", "onStartCommand: 222");
        new AutoPresenter(new AutoCallBack() {
            @Override
            public void LogData(LogBean logBean) {
                Log.i("zx", "LogData: "+logBean.toString());
                if (logBean!=null){
                    if (logBean.getCode().equals("200")){
                        Log.i("zx", "LogData: 成功");
                        UserCallBack.getInstance().setName(logBean.getResult().getName());
                        FiannceUserManager.getInstance().setIsLog(logBean);
                        Squilts.putString(AutoService.this,logBean.getResult().getToken());
                    }
                }
            }
            @Override
            public void ShowLoading() {

            }

            @Override
            public void HideLoading() {

            }

            @Override
            public void Error(String error) {
                Log.i("zx", "Error: "+error);
            }
        }).RequestAuto(Squilts.getString(this));
        return super.onStartCommand(intent, flags, startId);
    }
}