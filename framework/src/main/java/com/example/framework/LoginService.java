package com.example.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.example.commom.FiannceConstants;
import com.example.commom.SpUtil;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.NetModel;
import com.example.net.RetrofitCreator;
import com.example.net.mode.AutoLoginBean;
import com.example.net.mode.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d("123");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String string = SpUtil.getString(LoginService.this, FiannceConstants.TOKEN_KEY);

        LogUtils.e(string);
//        if (!string.equals("")){
            RetrofitCreator.getFiannceApiService()
                    .getAuto(string)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull LoginBean loginBean) {
                            if (loginBean.getCode().equals("200")){
                                FiannceUserManager.getInstance()
                                        .setLoginBean(loginBean);

                                SpUtil.setString(NetModel.context,loginBean.getResult().getToken());
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
//        }
        return super.onStartCommand(intent, flags, startId);
    }
}
