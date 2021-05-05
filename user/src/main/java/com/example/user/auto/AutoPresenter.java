package com.example.user.auto;

import android.util.Log;

import com.example.framwork.base.BasePresenter;
import com.example.network.model.LogBean;
import com.example.network.retrofit.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutoPresenter extends BasePresenter<AutoCallBack> {
    public AutoPresenter(AutoCallBack callBack) {
        addThouView(callBack);
    }
    public void RequestAuto(String token){
        Log.i("zx", "token: "+token);
        RetrofitManager.getRetrofit()
                .AutoData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LogBean logBean) {
                        if (logBean!=null){
                            iView.LogData(logBean);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
