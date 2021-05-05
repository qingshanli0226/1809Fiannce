package com.example.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.commom.FianceConstants;
import com.example.commom.SpUtil;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.RetrofitCreator;
import com.example.net.model.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FiannceService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String token = SpUtil.getString(this, FianceConstants.TOKEN_KEY);
        RetrofitCreator.getFiannceApiService().getAutoLoginData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (loginBean.getCode().equals("200")) {
                            FiannceUserManager.getInstance().setLoginBean(loginBean);
                            SpUtil.setString(FiannceService.this, FianceConstants.TOKEN_KEY, loginBean.getResult().getToken());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return super.onStartCommand(intent, flags, startId);
    }
}
